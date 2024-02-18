package cn.kiroe.index.market.frontdesk.config;

import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 需要组件： ShiroFilter,SecurityManager,Realms,SessionManager
 */
@Configuration
public class ShiroConfig {


    // 配置自定义Realm
    // 获取认证信息和 权限信息的对象，也就是提供了获取认证信息和权限信息的方法
    // 这些方法是个性化的业务 -> 需要自己开发
    // 在Realm中提供了注解
    // @Bean
    // public MarketRealm marketRealm() {
    //     return new MarketRealm();
    // }

    // 注册ShiroSessionManager
    @Bean
    public SessionManager customShiroSessionManager(){
        return new CustomShiroSessionManager();
    }
    // 配置安全管理器
    @Bean
    public DefaultWebSecurityManager securityManager(MarketRealm marketRealm,SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(marketRealm);
        securityManager.setSessionManager(sessionManager);
        // 这两个是默认的认证器和 授权器
        // securityManager.setAuthenticator(new ModularRealmAuthenticator());
        // securityManager.setAuthorizer(new ModularRealmAuthorizer());
        return securityManager;
    }


    // 配置Shiro过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 允许匿名访问的路径
        filterChainDefinitionMap.put("/**", "anon");
        // 需要认证才能访问的路径
        filterChainDefinitionMap.put("/wx/cart/*", "authc");
        filterChainDefinitionMap.put("/wx/user/*", "authc");
        filterChainDefinitionMap.put("/wx/collect/*", "authc");
        filterChainDefinitionMap.put("/wx/address/*", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 下面2个支持controller层注解实现权限控制
     *     @RequiresAuthentication
     * @return
     */
    // @Bean(name = "advisorAutoProxyCreator")
    // public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
    //     DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    //     advisorAutoProxyCreator.setProxyTargetClass(true);
    //     advisorAutoProxyCreator.setUsePrefix(true);
    //     return advisorAutoProxyCreator;
    // }

    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}