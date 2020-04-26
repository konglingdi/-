package com.kld.dao;

import com.kld.bean.UserInfo;

public interface IUserDao {

    /**
     * 根据用户名查询用户信息（包括角色信息）
     * @param username
     * @return
     */
    UserInfo findUserInfoByUserName(String username);
}
