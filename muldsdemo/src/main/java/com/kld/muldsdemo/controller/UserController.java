package com.kld.muldsdemo.controller;

import com.kld.muldsdemo.annotation.CurDataSource;
import com.kld.muldsdemo.entity.db1.Users;
import com.kld.muldsdemo.entity.db2.User;
import com.kld.muldsdemo.pojo.DataSourceContextHolder;
import com.kld.muldsdemo.service.UserService;
import com.kld.muldsdemo.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @ClassName UserController
 * @date: 2020.08.31 15:57
 * @Author: 孔令迪
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersService usersService;

    @GetMapping("findAll")
    public List<User> findAll(String dataSourceName){
        List<Users> all = usersService.findAll();
        log.info("db1 users 表的数据{}",all);
        DataSourceContextHolder.setDataSource("db2DataSource");
        List<User> users = userService.selectAll();
        log.info("db2 user 表数据{}",users);
        DataSourceContextHolder.setDataSource("db3DataSource");
        List<User> users1 = userService.selectAll();
        log.info("db3 user 表数据{}",users1);
        List<Users> all1 = usersService.findAll();
        log.info("db1 users 表的数据{}",all1);
        return users;
    }

    @PostMapping("insertOne")
    public Map<String, Object> insertOne(@RequestBody User user){
        int i = userService.insertOne(user);
        if(i == 1){
            return new HashMap<String, Object>(){{
              put("code",200);
              put("rows",i);
              put("message","插入成功");
            }};
        }else{
            return new HashMap<String, Object>(){
                {
                    put("code",200);
                    put("rows",i);
                    put("message","插入失败");
                }
            };
        }

    }

    @GetMapping("/test")
    @CurDataSource("db3DataSource")
    public List<User> test(){
        return  userService.selectAll();
    }
}
