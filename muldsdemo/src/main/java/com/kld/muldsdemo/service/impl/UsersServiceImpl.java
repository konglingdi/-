package com.kld.muldsdemo.service.impl;

import com.kld.muldsdemo.entity.db1.Users;
import com.kld.muldsdemo.mapper.db1.UsersMapper;
import com.kld.muldsdemo.service.UserService;
import com.kld.muldsdemo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @ClassName UsersServiceImpl
 * @date: 2020.08.31 15:06
 * @Author: 孔令迪
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Override
    public List<Users> findAll() {
        return usersMapper.findAll();
    }

    @Override
    public int insertOne(Users users) {
        return usersMapper.insertOne(users);
    }
}
