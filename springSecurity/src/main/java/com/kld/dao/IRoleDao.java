package com.kld.dao;

import com.kld.bean.Role;

import java.util.List;

public interface IRoleDao {

    List<Role> findRolesByUid(String uid);
}
