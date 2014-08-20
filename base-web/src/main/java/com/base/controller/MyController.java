package com.base.controller;

import com.base.PrintUtils;
import com.base.user.domain.User;
import com.base.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@Controller
public class MyController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(Model model){
        User user = userService.get(1);
        model.addAttribute("user",user);
        return "/index";
    }
}
