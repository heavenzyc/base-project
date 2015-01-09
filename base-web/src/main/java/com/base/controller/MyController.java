package com.base.controller;

import com.base.sys.manager.domain.Manager;
import com.base.sys.manager.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@Controller
public class MyController {

    @Resource
    private ManagerService managerService;

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
        return "/project/jGrid";
    }

    @RequestMapping(value = "/form")
    public String form(){
        return "/project/form";
    }
}
