package com.pl.shop.service;

import com.pl.shop.entity.Category;
import com.pl.shop.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService{

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getTypeList(int parentId) {
        List<Category> categoryList = categoryMapper.getTypeList(parentId);
        return categoryList;
    }
}
