package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.boot.system.JavaVersion.THIRTEEN;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/loginUser")
    public String login(User user, HttpServletRequest request){
        System.out.println("开始登陆。。。。。");
        User login = userService.login(user);
        if (null == login){
            return"用户名或者密码错误";
        }else{
            request.getSession().setAttribute("session-user",login);
                return "登陆成功";
        }
    }

    @RequestMapping("/toRegister")
    public String toregister(){
        return "register";
    }

    @RequestMapping("/register")
    public String register(User user){
        System.out.println("开始注册。。。。。。。。。。");
        int register = userService.register(user);
        if (0 == register){
            System.out.println("--------");
        }
            return "welcome";

    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/outUser")
    public void outUser(HttpServletRequest request , HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("session-user");
        response.sendRedirect("/user/toIndex");
    }

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }
}
