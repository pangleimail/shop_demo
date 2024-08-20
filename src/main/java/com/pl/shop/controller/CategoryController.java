package com.pl.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.pl.shop.entity.Category;
import com.pl.shop.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 查询所有父级类型
     * @return
     */
    @GetMapping("/getTypeList")
    @ResponseBody
    public Object parentTypeList(@RequestParam("parentId") int parentId){
        List<Category> categoryList = categoryService.getTypeList(parentId);
        return JSONObject.toJSONString(categoryList);
    }

}
