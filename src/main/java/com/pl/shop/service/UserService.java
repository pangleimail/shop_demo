package com.pl.shop.service;

import com.pl.shop.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    /**
     * 根据名字查询用户列表
     *
     * @param name
     * @return
     */
    List<User> userList(@Param("name") String name);

    /**
     * 用户登录
     *
     * @param phone
     * @return
     */
    String getUserLogin(@Param("phone") String phone, @Param("password") String password, @Param("verifyCode") String verifyCode, @Param("code") String code);

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据名字查询用户头像
     *
     * @param phone
     * @return
     */
    User getHeadImg(@Param("phone") String phone);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    int updateUser(User user);
}
