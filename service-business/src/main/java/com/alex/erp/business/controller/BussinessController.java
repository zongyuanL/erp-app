package com.alex.erp.business.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buss")
@Log4j2
public class BussinessController {


    @ApiOperation(value="带权限登录",notes = "带权限登录", httpMethod = "POST")
    @ApiImplicitParam(name="loginInfoVo",value="用户信息", required = true, dataType = "string")
    @GetMapping("/withright")
    @PreAuthorize("hasAuthority('query-demo')")
    public String getDemo(){
        log.warn("runing buss with right");
        return "good with right";
    }

    @GetMapping("/withhelloright")
    @PreAuthorize("hasAuthority('query')")
    public String getDemo3(){
        log.warn("runing buss with hello right");
        return "good with hello right";
    }

    @GetMapping("/withoutright")
    public String getDemo2(){

        log.warn("runing buss without right");
        return "good without right";
    }
}
