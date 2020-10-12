package com.kld.muldsdemo.service.impl;

import com.kld.muldsdemo.entity.db2.User;
import com.kld.muldsdemo.mapper.db2.UserMapper;
import com.kld.muldsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

/**
 * @Description:
 * @ClassName UserServiceImpl
 * @date: 2020.08.31 15:07
 * @Author: 孔令迪
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        List<User> users = userMapper.selectAll();




        return users;

    }

    @Override
    public int insertOne(User user) {
        return userMapper.insertOne(user);
    }
}
