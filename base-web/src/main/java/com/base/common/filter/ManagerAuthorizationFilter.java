package com.base.common.filter;

import com.base.sys.manager.domain.Manager;
import com.base.sys.manager.service.ManagerService;
import com.base.sys.sys_resource.model.SysResource;
import com.base.sys.sys_resource.service.SysResourceService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by zkdu on 2015/1/19.
 */
public class ManagerAuthorizationFilter implements Filter {

    private ServletContext ctx = null;
    private SysResourceService sysResourceService = null;
    private ManagerService managerService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ctx = filterConfig.getServletContext();
        sysResourceService = (SysResourceService) WebApplicationContextUtils.getWebApplicationContext(ctx).getBean("sysResourceService");
        managerService = (ManagerService) WebApplicationContextUtils.getWebApplicationContext(ctx).getBean("managerService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        StringBuffer url = request.getRequestURL();
        List<SysResource> menus = (List<SysResource>) session.getAttribute("menus");
        Manager manager = (Manager) session.getAttribute("manager");
        if (url.toString().endsWith("/login.htm")) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if (manager == null ) {
            response.sendRedirect("/login.htm");
            return;
        }
        if (menus == null || menus.size() == 0) {
            menus = sysResourceService.getManagerMenus(manager);
        }
        session.setAttribute("menus",menus);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
