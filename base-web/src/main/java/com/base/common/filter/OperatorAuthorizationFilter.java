package com.base.common.filter;

import com.base.sys.sys_resource.model.SysResource;
import com.base.sys.sys_resource.service.SysResourceService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by zkdu on 2015/1/19.
 */
public class OperatorAuthorizationFilter implements Filter {

    private ServletContext ctx = null;
    private SysResourceService sysResourceService = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ctx = filterConfig.getServletContext();
        sysResourceService = (SysResourceService) WebApplicationContextUtils.getWebApplicationContext(ctx).getBean("sysResourceService");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<SysResource> menus = sysResourceService.getAll();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.getSession().setAttribute("menus",menus);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
