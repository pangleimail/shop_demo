package com.pl.shop.entity;

import lombok.Data;

/**
 * 商品类型
 */
@Data
public class Category {

    /**
     * 类型编号
     */
    private int id;

    /**
     * 父类型编号
     */
    private int parentId;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 类型状态（1.可用，0.停用）
     */
    private int status;

    /**
     * 类型创建时间
     */
    private String createTime;

    /**
     * 类型更新时间
     */
    private String updateTime;
}
