package com.pl.shop.entity;

import lombok.Data;

/**
 * 商品类
 */
@Data
public class Product {

    /**
     * 商品编号
     */
    private String id;

    /**
     * 商品类型编号
     */
    private int cateId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private double price;

    /**
     * 商品库存数量
     */
    private int stock;

    /**
     * 商品状态（1.在售，0.下架）
     */
    private int status;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 商品创建时间
     */
    private String createTime;

    /**
     * 商品信息更新时间
     */
    private String updateTime;

    /**
     * 商品图片地址
     */
    private String imgAddress;
}
