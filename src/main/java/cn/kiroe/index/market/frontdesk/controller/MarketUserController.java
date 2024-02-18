package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.vo.UserIndexVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.service.IMarketUserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Kiro
 * @Date 2024/01/03 14:21
 **/
@RestController
@RequestMapping("/wx/user/index")
@RequiredArgsConstructor
public class MarketUserController {

    private final IMarketUserService userService;
    @GetMapping
    @RequiresAuthentication
    public BaseRespVo<UserIndexVo> index(){
        /**
         * order: {unrecv: 0, uncomment: 5, unpaid: 0, unship: 29}
         * uncomment: 5
         * unpaid: 0
         * unrecv: 0
         * unship: 29
         */
        // 获取订单
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getPrincipal();
        UserIndexVo index = userService.index(userId);
        return BaseRespVo.ok(index);
    }
}
