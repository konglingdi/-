package com.kld.muldsdemo.service;

import com.kld.muldsdemo.entity.db1.Users;
import com.kld.muldsdemo.entity.db2.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description:
 * @ClassName UsersServiceTest
 * @date: 2020.08.31 15:10
 * @Author: 孔令迪
 */
@SpringBootTest
public class UsersServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UsersService usersService;

    @Test
    public void selectAllTest(){
        List<User> users = userService.selectAll();
        System.out.println(users);

        List<Users> all = usersService.findAll();
        System.out.println(all);
    }

    @Test
    public void findAllTest(){
        List<User> users = userService.selectAll();
        System.out.println(users);
    }
}
