package com.zyc.zuulgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.RequestContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {


    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    /**
     * 过滤器的类型，
     * 它决定在请求的哪个生命周期进行
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }


    /**
     * 判断该过滤器是否需要执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext crc = RequestContext.getCurrentContext();
        HttpServletRequest request = crc.getRequest();
        log.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            log.info("token is not null!!");
            crc.setSendZuulResponse(false);
            crc.setResponseStatusCode(401);
            return null;
        }
        log.info("token: {}",token);
        return null;
    }
}
