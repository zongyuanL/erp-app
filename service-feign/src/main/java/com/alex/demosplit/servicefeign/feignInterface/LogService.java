package com.alex.demosplit.servicefeign.feignInterface;

import com.alex.erp.basic.vo.LoginInfoVo;
import com.alex.erp.basic.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "service-log")
public interface LogService {

    @RequestMapping(value = "/api/login",method = RequestMethod.GET)
    Result logFromClientOne(@RequestBody LoginInfoVo loginInfoVo);
}

