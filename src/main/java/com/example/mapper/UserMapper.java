package com.example.mapper;

import com.example.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    public User findById( int id);

    public User login(String userName , String passWord);

    public int register(User user);
}
