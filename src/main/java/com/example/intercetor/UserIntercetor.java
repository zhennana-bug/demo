package com.example.intercetor;

import com.example.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserIntercetor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("执行了preHandle方法");

        String id = request.getSession().getId();
        System.out.println("sessionid： "+id);

        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("JSESSIONID")){
                System.out.println("JSESSIONID： "+cookies[i].getValue());
            }
        }

        User user = (User) request.getSession().getAttribute("session-user");
        if (null ==  user){
            System.out.println("成功拦截");
            response.sendRedirect(request.getContextPath()+"/user/toIndex");
            return false;
        }
        System.out.println("不需要拦截放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("执行了posthandle方法");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("执行到了afterCompletion方法");
    }
}
