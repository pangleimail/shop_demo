package com.pl.shop.service;

import com.pl.shop.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService {

    /**
     * 查询所有父级类型
     * @param parentId
     * @return
     */
    List<Category> getTypeList(@Param("parentId") int parentId);

}
