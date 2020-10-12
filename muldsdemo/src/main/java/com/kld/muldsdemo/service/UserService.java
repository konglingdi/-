package com.kld.muldsdemo.service;

import com.kld.muldsdemo.entity.db2.User;

import java.util.List;

/**
 * @Description:
 * @ClassName UserService
 * @date: 2020.08.31 15:06
 * @Author: 孔令迪
 */
public interface UserService {

    public List<User> selectAll();

    public int insertOne(User user);
}
