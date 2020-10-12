package com.kld.muldsdemo.service;

import com.kld.muldsdemo.entity.db1.Users;

import java.util.List;

/**
 * @Description:
 * @ClassName UsersService
 * @date: 2020.08.31 15:05
 * @Author: 孔令迪
 */
public interface UsersService {
    public List<Users> findAll();

    public int insertOne(Users users);
}
