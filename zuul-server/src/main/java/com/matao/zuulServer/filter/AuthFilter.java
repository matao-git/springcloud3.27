package com.matao.zuulServer.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends ZuulFilter {
    /**
     *   * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类
     * 型，具体如下：
     *   * pre：路由之前
     *   * routing：路由之时
     *   * post： 路由之后
     *   * error：发送错误调用
     *   
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤，true:永远要过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * 可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                /** 表示不进行路由，不会被zuul转发到后端服务器 **/
                currentContext.setSendZuulResponse(false);
                /** http状态码，表示未授权 **/
                currentContext.setResponseStatusCode(401);
                HttpServletResponse response = currentContext.getResponse();
                /** 设置返回的内容类型 **/
                response.setContentType("application/json;charset=utf-8");
                try {
                    /** 输出返回的结果 **/
                    response.getWriter().write("{\"message\":\"未授权\"}");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
    }
}