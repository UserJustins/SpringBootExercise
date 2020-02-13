package com.justin.sprigboot_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class HelloController {
    @PostMapping("/user/login")
    public String hello(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        Map<String,Object> map, HttpSession session){
        if (username != null && "123".equals(password)){
            session.setAttribute("loginName",username);
            //登录成功，页面重定向防止表单重复提交
            return "redirect:/main.html";
        }else {
            //登录失败，并设置错误的信息
            map.put("msg","用户名或密码错误");
            return "index";
        }

    }
}
