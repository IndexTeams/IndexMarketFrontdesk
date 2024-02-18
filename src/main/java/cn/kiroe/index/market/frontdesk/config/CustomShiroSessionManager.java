package cn.kiroe.index.market.frontdesk.config;

import cn.hutool.core.text.CharSequenceUtil;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 跨域场景下Session会发生变化，保证Session不变
 * 认证完成之后，把SessionId作为响应结果响应给前端，前端发送请求，携带了SessionId
 * 通过请求头携带了SessionId信息
 *
 * 会话管理器要处理通过请求头获得SessionId这个过程
 * @author stone
 */
public class CustomShiroSessionManager extends DefaultWebSessionManager {

    public static final String HEADER = "X-CskaoyanMarket-Token";

    @Override
    protected Serializable getSessionId(ServletRequest servletRequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String sessionId = request.getHeader(HEADER);
        if (!CharSequenceUtil.isEmpty(sessionId)) {
            return sessionId;
        }
        return super.getSessionId(servletRequest, response);
    }


}