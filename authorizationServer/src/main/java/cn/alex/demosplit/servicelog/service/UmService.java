package cn.alex.demosplit.servicelog.service;


import cn.alex.demosplit.dsbasic.vo.LoginInfoVo;
import cn.alex.demosplit.dsbasic.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-um")
public interface UmService {


    @RequestMapping(value = "/oauth/token",method = RequestMethod.POST)
    Result logFromClientOne(@RequestBody LoginInfoVo loginInfoVo);
}
