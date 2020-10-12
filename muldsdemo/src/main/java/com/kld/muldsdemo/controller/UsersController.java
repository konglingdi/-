package com.kld.muldsdemo.controller;

import com.kld.muldsdemo.entity.db1.Users;
import com.kld.muldsdemo.entity.db2.User;
import com.kld.muldsdemo.service.UserService;
import com.kld.muldsdemo.service.UsersService;
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
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("findAll")
    public List<Users> findAll(){
        List<Users> users = usersService.findAll();
        return users;
    }

    @PostMapping("insertOne")
    public Map<String, Object> insertOne(@RequestBody Users user){
        int i = usersService.insertOne(user);
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
}
