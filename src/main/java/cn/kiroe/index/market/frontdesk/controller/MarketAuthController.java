package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketUser;
import cn.kiroe.index.market.frontdesk.dao.vo.AuthLoginParamVo;
import cn.kiroe.index.market.frontdesk.dao.vo.AuthLoginVo;
import cn.kiroe.index.market.frontdesk.dao.vo.AuthRegisterVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.service.IMarketUserService;
import cn.kiroe.index.market.frontdesk.util.CheckCodeHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Author Kiro
 * @Date 2024/01/02 14:28
 **/
@Slf4j
@Tag(name = "登入接口")
@RestController()
@RequestMapping("/wx/auth/")
@RequiredArgsConstructor
public class MarketAuthController {

    private final IMarketUserService userService;

    /*
     * :8083/wx/auth/login
     * 使用shiro管理，写个Demo
     * 登入 发送jwt给 客户
     * 并解析jwt，获取用户的 id
     * 先弄个最简单的把，之后再改
     * */
    @PostMapping("/login")
    public BaseRespVo<AuthLoginVo> login(@Valid @RequestBody AuthLoginParamVo authLoginParamVo) {
        String username = authLoginParamVo.getUsername();
        String password = authLoginParamVo.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            // 执行过程 -> subject.login -> securityManager.login -> authenticator(认证器)
            subject.login(usernamePasswordToken);
            Session session = subject.getSession();
            log.info("登入session:{}", session.getId());
            Integer userId = (Integer) subject.getPrincipal();

            MarketUser userInfo = userService.getOne(new LambdaQueryWrapper<MarketUser>().eq(MarketUser::getId, userId));
            // 放入 视图中
            AuthLoginVo.UserInfoDTO userInfoDTO = AuthLoginVo.UserInfoDTO
                    .builder().nickName(userInfo.getNickname())
                    .avatarUrl(userInfo.getAvatar())
                    .build();
            AuthLoginVo userLoginVo = AuthLoginVo
                    .builder()
                    .token(session.getId().toString())
                    .userInfo(userInfoDTO)
                    .build();

            return BaseRespVo.ok(userLoginVo);
        } catch (Exception e) {
            log.warn("用户 {} 登入未通过鉴权", username);
            return BaseRespVo.fail("账号或者密码错误");
        }
    }

    @PostMapping("/logout")
    @RequiresAuthentication
    public BaseRespVo logout() {
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getPrincipal();

        log.info("id:{} 已登出", userId);
        // 清除session
        subject.logout();
        return BaseRespVo.ok();
    }

    @PostMapping("/register")// @Valid判空
    public BaseRespVo register(@Valid @RequestBody AuthRegisterVo registerVo) {
        // CheckCodeHolder静态方法保存 手机号-验证码 解决线程安全问题
        // 由于生成并发送验证码 和 用户发送验证码服务器校验是两个分开的行为/方法 所以需要一个静态Map去保存手机号验证码
        if (!CheckCodeHolder.getCode(registerVo.getMobile()).equals(registerVo.getCode())) {
            return BaseRespVo.fail("验证码错误");
        }
        String mobile = registerVo.getMobile();
        String username = registerVo.getUsername();
        String password = registerVo.getPassword();

        MarketUser newUser = MarketUser.builder()
                // 在这里可以进行加盐做加密
                .username(username)
                .nickname(username)
                .password(password)
                .mobile(mobile)
                .build();

        //  1.手机号已注册
        //     data{"errno":705,"errmsg":"手机号已注册"}
        MarketUser existed = userService.getOne(new LambdaQueryWrapper<MarketUser>()
                .eq(MarketUser::getMobile, mobile));
        if (existed != null) {
            return BaseRespVo.fail(705, "手机号已注册");
        }

        boolean saved = userService.save(newUser);
        //  2.注册失败
        if (!saved) {
            return BaseRespVo.fail("注册失败");
        }

        // 3.注册成功
        AuthLoginParamVo loginParam = new AuthLoginParamVo(username, password);
        return this.login(loginParam);
    }

    @PostMapping("/regCaptcha")
    public BaseRespVo regCaptcha(@RequestBody Map<String, String> map) {
        String result = userService.sendCheckCode(map.get("mobile"));
        if (result != null) {

            CheckCodeHolder.save(map.get("mobile"), result);
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("发送失败");
    }

}
