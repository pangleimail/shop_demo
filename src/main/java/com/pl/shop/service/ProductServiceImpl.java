package com.pl.shop.service;

import com.pl.shop.entity.ParentMap;
import com.pl.shop.entity.Product;
import com.pl.shop.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> getProductList(ParentMap parentMap) {
        int num = parentMap.getCurrentNum();
        int currentNum = (num - 1) * parentMap.getPageSize();
        parentMap.setCurrentNum(currentNum);
        List<Product> productList = productMapper.getProductList(parentMap);
        return productList;
    }

    @Override
    public int getProductCount(ParentMap parentMap) {
        int num = parentMap.getCurrentNum();
        int currentNum = (num - 1) * parentMap.getPageSize();
        parentMap.setCurrentNum(currentNum);
        int count = productMapper.getProductCount(parentMap);
        return count;
    }

    @Override
    public int delProduct(String[] idArray) {
        int flag = productMapper.delProduct(idArray);
        return flag;
    }

    @Override
    public int addProduct(Product product) {
        int flag = productMapper.addProduct(product);
        return flag;
    }
}
