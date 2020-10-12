package com.kld.muldsdemo.mapper.db1;

import com.kld.muldsdemo.entity.db1.Users;

import java.util.List;

public interface UsersMapper {
    public List<Users> findAll();

    public int insertOne(Users users);
}