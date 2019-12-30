package com.alex.erp.business.controller;


import com.alex.erp.basic.vo.Menu;
import com.alex.erp.basic.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buss")
@Log4j2
public class BussinessController {


    @ApiOperation(value="带权限登录",notes = "带权限登录", httpMethod = "POST")
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

    @ApiOperation(value="无权限测试接口",notes = "没有 hasAuthority 选项", httpMethod = "GET")
    @ApiImplicitParam(name="username",value="用户名", required = false,defaultValue = "alex",dataType = "string")
    @GetMapping("/withoutright")
    public Result<Menu> getDemo2(@RequestParam(value="username",defaultValue = "alex") String userName ){

        log.warn("runing buss without right");
//        return "hi @"+userName+",that is good without right";
        return null;
    }

}
