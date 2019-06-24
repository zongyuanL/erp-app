package cn.alex.demosplit.scgateway.controller;

import cn.alex.demosplit.dsbasic.vo.Result;
import cn.alex.demosplit.dsbasic.vo.factory.ResultFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {

    @RequestMapping("/defaultfallback")
    public Result defaultfallback(){
       return ResultFactory.buildServiceBrokendown("服务异常");

    }

}
