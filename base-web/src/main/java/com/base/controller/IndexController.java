package com.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zkdu on 2015/1/19.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public ModelAndView login(HttpServletRequest request){
        ModelAndView model = new ModelAndView();

        model.setViewName("/project/jGrid");
        return model;
    }
}
