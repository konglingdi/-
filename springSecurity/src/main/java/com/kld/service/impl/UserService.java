package com.kld.service.impl;

import com.kld.bean.Role;
import com.kld.bean.UserInfo;
import com.kld.dao.IUserDao;
import com.kld.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findUserInfoByUserName(s);

        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),disposeRoles(userInfo.getRoleList()));
        return user;
    }

    private List<GrantedAuthority> disposeRoles(List<Role> roleList){
        List<GrantedAuthority> rtList = new ArrayList<>();
        for(Role role : roleList){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
            rtList.add(grantedAuthority);
        }
        return rtList;
    }
}
