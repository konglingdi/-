package com.kld.muldsdemo.mapper.db2;

import com.kld.muldsdemo.entity.db2.User;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public List<User> selectAll();

    public int insertOne(User user);
}