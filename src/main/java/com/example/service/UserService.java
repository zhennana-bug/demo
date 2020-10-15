package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(int id){
        return userMapper.findById(id);
    }

    public User login(User user){
       return userMapper.login(user.getUserName(),user.getPassWord());
    };

    public int register(User user){
        return  userMapper.register(user);
    }
}
