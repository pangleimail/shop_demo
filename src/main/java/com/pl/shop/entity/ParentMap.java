package com.pl.shop.entity;

import lombok.Data;

/**
 * 商品参数类
 */
@Data
public class ParentMap {

    /**
     * 子类型编号
     */
    private int cateId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 父类型编号
     */
    private int parentId;

    /**
     * 当前页
     */
    private int currentNum;

    /**
     * 每页显示个数
     */
    private int pageSize;
}
