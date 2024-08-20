package com.pl.shop.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件上传
 *
 * @description: hehsmk
 * @date: 2023/8/15
 */
public class FileUtil {


    public static String uploadFile(MultipartFile multipartFile, String fileName) {
        //判断文件是否为空 isEmpty
        if (multipartFile == null) {
            return "文件为空";
        }
        //构建日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateTime = simpleDateFormat.format(date.getTime());
        //获取文件的原名称 getOriginalFilename
        String OriginalFilename = multipartFile.getOriginalFilename();
        //获取时间戳和文件的扩展名，拼接成一个全新的文件名； 用时间戳来命名是为了避免文件名冲突
//        String fileName = System.currentTimeMillis() + "." + OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1);
        //定义文件存放路径
        String filePath = "D:\\Desktop\\code\\shop_demo\\src\\main\\resources\\static\\images";
        //新建一个目录（文件夹）
        String newpath = filePath + "/" + fileName;
        File dest = new File(newpath);
        //判断filePath目录是否存在，如不存在，就新建一个
        if (!dest.getParentFile().canExecute()) {
            dest.getParentFile().mkdirs(); //新建一个目录
        }
        try {
            //文件输出
            multipartFile.transferTo(dest);
            dest.setReadable(true, false);
            dest.setExecutable(false);
        } catch (Exception e) {
            e.printStackTrace();
            //拷贝失败要有提示
            return "上传失败";
        }
        return newpath;

    }

    //上传路径存放配置文件
    public static String newUploadFile(MultipartFile multipartFile, String account, String uploadfileimgurl) {
        //判断文件是否为空 isEmpty
        if (multipartFile == null) {
            return "文件为空";
        }
        //构建日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateTime = simpleDateFormat.format(date.getTime());
        //获取文件的原名称 getOriginalFilename
        String OriginalFilename = multipartFile.getOriginalFilename();
        //获取时间戳和文件的扩展名，拼接成一个全新的文件名； 用时间戳来命名是为了避免文件名冲突
        String fileName = System.currentTimeMillis() + "." + OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1);
        //定义文件存放路径
        // String filePath = "D:\\Test\\";
        //新建一个目录（文件夹）
        String newpath = uploadfileimgurl + account + "/" + dateTime + "/" + fileName;
        File dest = new File(newpath);
        //判断filePath目录是否存在，如不存在，就新建一个
        if (!dest.getParentFile().canExecute()) {
            dest.getParentFile().mkdirs(); //新建一个目录
        }
        try {
            //文件输出
            multipartFile.transferTo(dest);
            dest.setReadable(true, false);
            dest.setExecutable(false);
        } catch (Exception e) {
            e.printStackTrace();
            //拷贝失败要有提示
            return "上传失败";
        }
        // 从后向前查找第一个"/"的位置
        int firstIndex = newpath.lastIndexOf("/");
        while (firstIndex != -1 && !newpath.substring(firstIndex + 1).startsWith("upload-image")) {
            firstIndex = newpath.lastIndexOf("/", firstIndex - 1);
        }

// 截取该位置之后的子字符串
        String subPath = newpath.substring(firstIndex);
        return subPath;

    }
}