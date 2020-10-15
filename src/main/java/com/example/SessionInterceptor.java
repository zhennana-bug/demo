package com.example;

import com.example.intercetor.UserIntercetor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class SessionInterceptor implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList<>();
        list.add("/user/toIndex");
        list.add("/user/loginUser");
        list.add("/user/toRegister");
        list.add("/user/register");
        registry.addInterceptor(new UserIntercetor()).addPathPatterns("/**").excludePathPatterns(list);
    }
}
