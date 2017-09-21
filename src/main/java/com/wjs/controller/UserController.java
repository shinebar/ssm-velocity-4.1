package com.wjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wjs.model.User;
import com.wjs.service.IUserService;

@Controller
@RequestMapping("user")
public class UserController {
     
     @Autowired
     private IUserService userService;
     
     @ResponseBody
     @RequestMapping("register")
     public boolean register(@RequestParam("username") String username,
                             @RequestParam("password") String password){
         User user = new User();
         user.setUserName(username);
         
         boolean isRegisterSuccess = userService.register(user);
         
         return isRegisterSuccess;
     }
     
     @RequestMapping("login")
     public ModelAndView login(@RequestParam("username") String username,
                               @RequestParam("password") String password){
         User user = userService.login(username, password);
         ModelAndView modelAndView = new ModelAndView();
         if(user == null){
             modelAndView.addObject("message", "用户不存在或者密码错误！请重新输入");
             modelAndView.setViewName("error");
         }else{
             modelAndView.addObject("user", user);
             modelAndView.setViewName("userinfo");
         }
         
         return modelAndView;
     }
 }
