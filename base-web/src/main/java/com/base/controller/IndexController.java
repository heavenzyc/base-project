package com.base.controller;

import com.base.sys.sys_resource.model.SysResource;
import com.base.sys.sys_resource.service.SysResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zkdu on 2015/1/19.
 */
@Controller
@RequestMapping(value = "/se")
public class IndexController {

    @Resource
    private SysResourceService sysResourceService;

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request){
        List<SysResource> list = sysResourceService.getAll();
        ModelAndView model = new ModelAndView();
//        model.addObject("menus",list);
//        request.getSession().setAttribute("menus",list);
        model.setViewName("/login");
        return model;
    }
}
