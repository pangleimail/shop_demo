package com.pl.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.pl.shop.entity.ParentMap;
import com.pl.shop.entity.Product;
import com.pl.shop.service.ProductService;
import com.pl.shop.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    /**
     * 查询所有商品列表
     *
     * @return
     */
    @GetMapping("/getProductList")
    @ResponseBody
    public Object getProductList(ParentMap parentMap) {
        System.out.println("-----------" + parentMap.getCateId() + "---" + parentMap.getName() + "-----" + parentMap.getCateId() + "-----" + parentMap.getCurrentNum() + "-----" + parentMap.getPageSize());
        List<Product> productList = productService.getProductList(parentMap);
        return JSONObject.toJSONString(productList);
    }

    /**
     * 查询所有商品列表
     *
     * @return
     */
    @GetMapping("/getProductCount")
    @ResponseBody
    public Object getProductCount(ParentMap parentMap) {
        int count = productService.getProductCount(parentMap);
        int countNum = (count + parentMap.getPageSize() - 1) / parentMap.getPageSize();
        System.out.println("--------------" + countNum);
        return JSONObject.toJSONString(countNum);
    }

    /**
     * 根据id删除商品数据
     *
     * @return
     */
    @GetMapping("/delProduct")
    @ResponseBody
    public Object delProduct(String[] idArray) {
        System.out.println("id的值--------------" + idArray);
        int flag = productService.delProduct(idArray);
        System.out.println("是否删除成功--------------" + flag);
        return JSONObject.toJSONString(flag);
    }

    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    @PostMapping("/addProduct")
    @ResponseBody
    public Object addProduct(Product product) {
        System.out.println("product  名称的值--------------" + product.getName());
        int flag = productService.addProduct(product);
        System.out.println("是否添加成功--------------" + flag);
        return JSONObject.toJSONString(flag);
    }

    /**
     * 商品图片上传
     *
     * @param file
     * @param fileName
     * @return
     */
    @PostMapping("/fileFormData")
    @ResponseBody
    public Object fileFormData(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        String data = FileUtil.uploadFile(file, fileName);
        return JSONObject.toJSONString(data);
    }
}
