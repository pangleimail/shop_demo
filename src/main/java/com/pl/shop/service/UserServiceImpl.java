package com.pl.shop.service;

import com.pl.shop.entity.User;
import com.pl.shop.mapper.UserMapper;
import com.pl.shop.utils.MD5Util;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> userList(@Param("name") String name) {
        return userMapper.userList(name);
    }

    @Override
    public String getUserLogin(String phone, String password, String verifyCode, String code) {
        String md5Password = MD5Util.MD5(password);
        System.out.println("加密后的密码：" + md5Password);
        User user = userMapper.getUserLogin(phone, md5Password);
        System.out.println(verifyCode + "-----------------" + code);
        System.out.println(verifyCode + "-------11111111111111" + code);
        if (user == null) {
            return "0";
        } else if (verifyCode.equals("") || !verifyCode.equalsIgnoreCase(code)) {
            return "2";
        } else {
            return "1";
        }
    }

    @Override
    public int insertUser(User user) {
        String md5Password = MD5Util.MD5(user.getPassword());
        user.setPassword(md5Password);
        int num = userMapper.insertUser(user);
        return num;
    }

    @Override
    public User getHeadImg(String phone) {
        return userMapper.getHeadImg(phone);
    }

    @Override
    public int updateUser(User user) {
        String headImg = user.getHeadImg();
        String name = user.getName();
        String password = user.getPassword();
        if (!"".equals(headImg) && !"".equals(name) && !"".equals(password)) {
            user.setName(name);
            user.setPassword(password);
            user.setHeadImg(headImg);
            int nam = userMapper.updateUser(user);
            return nam;
        } else {
            return 0;
        }
    }


}
