package com.pl.shop.common;

import lombok.Data;

@Data
public class Result<T> {

    private String code;
    private String msg;
    private T data;

    public Result(){

    }

    public Result(T data){
        this.data = data;
    }

    /***
     * 成功返回
     * @return
     */
    public static Result success(){
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    /***
     * 带参数成功返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    /**
     * 错误信息返回给前端
     * @param code
     * @param msg
     * @return
     */
    public static Result error(String code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
