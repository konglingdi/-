package com.github.kld.shiro.chapter1.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @Description:
 * @ClassName MyRealm2
 * @date: 2020.08.21 16:53
 * @Author: 孔令迪
 */
public class MyRealm2 implements Realm {
    @Override
    public String getName() {
        return "myrealm2";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        if(!"xxx".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"xxx".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
