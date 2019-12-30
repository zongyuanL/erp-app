package com.alex.erp.wechat.service;

import com.alex.erp.basic.baseconfig.auth.TokenFeignClientInterceptor;
import com.alex.erp.basic.vo.Result;
import com.alex.erp.dbutil.um.entity.EsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-08-06 5:40 PM
 */
@FeignClient(value = "authorization",configuration = TokenFeignClientInterceptor.class)
public interface AuthorizeService {

    @RequestMapping(value = "/appLogin",method = RequestMethod.POST)
    Result<EsMember> login(@RequestBody Map object);
}