package com.pl.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class SkitController {

    @RequestMapping("/index")
    public String index(){
       return "index";
    }

    @RequestMapping("/regist")
    public String regist(){
       return "regist";
    }

    @RequestMapping("/info")
    public String info(){
        return "info";
    }

    @RequestMapping("/pass")
    public String pass(){
        return "pass";
    }

    @RequestMapping("/page")
    public String page(){
        return "page";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/adv")
    public String adv(){
        return "adv";
    }

    @RequestMapping("/book")
    public String book(){
        return "book";
    }

    @RequestMapping("/column")
    public String column(){
        return "column";
    }

    @RequestMapping("/list")
    public String list(){
        return "list";
    }

    @RequestMapping("/add")
    public String add(){
        return "add";
    }

    @RequestMapping("/cate")
    public String cate(){
        return "cate";
    }
}
