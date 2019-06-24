package cn.alex.demosplit.servicefeign.feignInterface;

import cn.alex.demosplit.dsbasic.vo.LoginInfoVo;
import cn.alex.demosplit.dsbasic.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "service-log")
public interface LogService {

    @RequestMapping(value = "/api/login",method = RequestMethod.GET)
    Result logFromClientOne(@RequestBody LoginInfoVo loginInfoVo);
}

