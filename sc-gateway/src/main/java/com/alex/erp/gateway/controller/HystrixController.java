package com.alex.erp.gateway.controller;

import com.alex.erp.basic.vo.Result;
import com.alex.erp.basic.vo.factory.ResultFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {

    @RequestMapping("/defaultfallback")
    public Result defaultfallback(){
       return ResultFactory.buildServiceBrokendown("服务异常");

    }

}
