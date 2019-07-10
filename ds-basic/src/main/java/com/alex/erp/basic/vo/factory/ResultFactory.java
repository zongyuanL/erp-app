package com.alex.erp.basic.vo.factory;


import com.alex.erp.basic.dic.ResultCode;
import com.alex.erp.basic.vo.Result;

public class ResultFactory {

    public static Result buildSuccessResult(Object data) {
        return buildSuccessResult(null, data);
    }

    public static Result buildSuccessResult() {
        return buildSuccessResult(null, null);
    }

    public static Result buildSuccessResult(String message) {
        return buildSuccessResult(message, null);
    }

    public static Result buildSuccessResult(String message, Object data) {
        return buidResult(ResultCode.SUCCESS, ResultCode.SUCCESS.getMessage()+message, data);
    }

    public static Result buildFailResult(int code,String message) {
        return buidResult(code,message, null);
    }
    public static Result buildFailResult(String message) {
        return buidResult(ResultCode.OTHER_FAILED, message, null);
    }
    public static Result buildServiceBrokendown(String message) {
        return buidResult(ResultCode.SERVICE_BROKENDOWN, message, null);
    }

    public static Result buidResult(ResultCode resultCode, String message, Object data) {
        return buidResult(resultCode.getCode(), message, data);
    }


    public static Result buidResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }


}
