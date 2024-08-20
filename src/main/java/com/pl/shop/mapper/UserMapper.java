package com.pl.shop.mapper;

import com.pl.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

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
    User getUserLogin(@Param("phone") String phone, @Param("password") String password);

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据手机号查询用户头像
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
