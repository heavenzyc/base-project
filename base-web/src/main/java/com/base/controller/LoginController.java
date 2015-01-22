package com.base.controller;

import com.base.sys.manager.domain.Manager;
import com.base.sys.manager.service.ManagerService;
import com.base.sys.sys_resource.model.SysResource;
import com.base.sys.sys_resource.service.SysResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zkdu on 2015/1/19.
 */
@Controller
public class LoginController {

    @Resource
    private ManagerService managerService;
    @Resource
    private SysResourceService sysResourceService;

    @RequestMapping(value = "/login.htm")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "/login";
    }
}
