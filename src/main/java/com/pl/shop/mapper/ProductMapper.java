package com.pl.shop.mapper;

import com.pl.shop.entity.ParentMap;
import com.pl.shop.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     * 查询所有商品列表
     *
     * @return
     */
    List<Product> getProductList(ParentMap parentMap);

    /**
     * 查询所有商品总记录数
     *
     * @return
     */
    int getProductCount(ParentMap parentMap);

    /**
     * 根据id删除商品数据
     *
     * @param idArray
     * @return
     */
    int delProduct(String[] idArray);

    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    int addProduct(Product product);
}
