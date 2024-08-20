package com.pl.shop.mapper;

import com.pl.shop.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 查询所有父级类型
     * @param parentId
     * @return
     */
    List<Category> getTypeList(@Param("parentId") int parentId);

}
