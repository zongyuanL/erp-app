package com.alex.erp.basic.vo;

import lombok.Data;

@Data
public class Result<T> {

    /**
     * 响应状态码
     */
    private int code;
    /**
     * 响应提示信息
     */
    private String message;
    /**
     * 响应结果对象
     */
    private T data;

    public Result(){
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
