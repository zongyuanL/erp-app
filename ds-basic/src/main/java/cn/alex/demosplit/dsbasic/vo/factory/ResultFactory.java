package cn.alex.demosplit.dsbasic.vo.factory;


import cn.alex.demosplit.dsbasic.dic.ResultCode;
import cn.alex.demosplit.dsbasic.vo.Result;

public class ResultFactory {

    public static Result buildSuccessResult(Object data) {
        return buildSuccessResult(ResultCode.SUCCESS.name(), data);
    }

    public static Result buildSuccessResult(String message, Object data) {
        return buidResult(ResultCode.SUCCESS, message, data);
    }

    public static Result buildFailResult(String message) {
        return buidResult(ResultCode.FAIL, message, null);
    }
    public static Result buildServiceBrokendown(String message) {
        return buidResult(ResultCode.SERVICE_BROKENDOWN, message, null);
    }

    public static Result buidResult(ResultCode resultCode, String message, Object data) {
        return buidResult(resultCode.code, message, data);
    }


    public static Result buidResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }


}
