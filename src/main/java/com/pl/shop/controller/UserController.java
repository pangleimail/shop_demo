package com.pl.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.pl.shop.entity.User;
import com.pl.shop.service.UserService;
import com.pl.shop.utils.RedisUtil;
import com.pl.shop.utils.VerifyCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 根据姓名查询用户列表
     *
     * @param name
     * @return
     */
    @GetMapping("/getUserList")
    @ResponseBody
    public Object getUserList(@RequestParam("name") String name) {
        List<User> userList = userService.userList(name);
        return JSONObject.toJSONString(userList);
    }

    /**
     * 用户登录
     *
     * @param phone
     * @param password
     * @param code
     * @return
     */
    @PostMapping("/getUserLogin")
    @ResponseBody
    public Object getUserLogin(@RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("code") String code) {
        Object verifyCode = redisUtil.get("verifyCode");
        if (verifyCode == null) {
            verifyCode = "";
        }
        System.out.println("verifyCode----------------:" + verifyCode);
        String num = userService.getUserLogin(phone, password, verifyCode.toString(), code);
        System.out.println("是否登录成功-----------" + num);
        return JSONObject.toJSONString(num);
    }

    /**
     * 生成验证码
     *
     * @return
     */
    @PostMapping("/verifyCode")
    @ResponseBody
    public String getVerifyCode() {
        //生成验证码
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        //验证码存入redis
        redisUtil.set("verifyCode", verifyCode, 60);
        return JSONObject.toJSONString(verifyCode);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Object insertUser(User user) {
        int num = userService.insertUser(user);
        return JSONObject.toJSONString(num);
    }

    /**
     * 根据姓名查询用户头像
     *
     * @param phone
     * @return
     */
    @GetMapping("/getHeadImg")
    @ResponseBody
    public Object getHeadImg(@RequestParam("phone") String phone) {
        User user = userService.getHeadImg(phone);
        return JSONObject.toJSONString(user);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    @ResponseBody
    public Object updateUser(User user) {
        int num = userService.updateUser(user);
        return JSONObject.toJSONString(num);
    }
}
