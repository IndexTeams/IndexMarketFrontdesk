package cn.kiroe.index.market.frontdesk.config;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketUser;
import cn.kiroe.index.market.frontdesk.service.IMarketUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;


/**
 * @Author Kiro
 * @Date 2024/01/02 20:24
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class MarketRealm extends AuthorizingRealm {

    private final IMarketUserService iMarketUserService;

    /**
     * 授权
     *
     * @param principalCollection the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principalCollection) {
        // 创建授权信息,用户 无权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     * 用于 鉴权
     *
     * @param authenticationToken the authentication token containing the user's principal and credentials.
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken authenticationToken) throws AuthenticationException {
        // 从token中获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 从数据库或其他存储中获取用户信息
        // 例如，从数据库中查询用户信息并返回
        MarketUser user = iMarketUserService.getOne(
                new LambdaQueryWrapper<MarketUser>()
                        .eq(MarketUser::getUsername, username));
        if (user == null) {
            return null;
        }
        String password = user.getPassword();
        log.info("账号:{},密码： {}", username, password);
        // 返回认证信息，包括用户id和密码
        return new SimpleAuthenticationInfo(user.getId(), password, getName());
    }
}
