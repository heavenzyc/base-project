package com.base.controller;

import com.base.sys.manager.domain.Manager;
import com.base.sys.manager.service.ManagerService;
import com.base.sys.sys_resource.model.SysResource;
import com.base.sys.sys_resource.service.SysResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@Controller
public class MyController {

    @Resource
    private ManagerService managerService;
    @Resource
    private SysResourceService sysResourceService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        List<Manager> user = managerService.findAll();
//        PrintUtils.print(user.getRole().getName());
        model.addAttribute("user",user);
        return "/index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "/login";
    }

    @RequestMapping(value = "/test")
    public String test(){
        return "/project/test";
    }

    @RequestMapping(value = "/jgrid")
    public String jgrid(){
        ModelAndView model = new ModelAndView();
        List<SysResource> sysResources = sysResourceService.getAll();
        model.addObject("menuList1",sysResources);
        return "/project/jGrid";
    }

    @RequestMapping(value = "/form")
    public String form(){
        return "/project/form";
    }
}
