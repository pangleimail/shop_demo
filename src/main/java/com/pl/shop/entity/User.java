package com.pl.shop.entity;

import lombok.Data;

@Data
public class User {

    /**
     * 编号
     */
    private long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 注册时间
     */
    private String registDate;
    /**
     * 备注
     */
    private String remark;

}
