package com.alex.erp.authorization.controller;

import com.alex.erp.authorization.service.AuthorizeService;
import com.alex.erp.authorization.service.MyUserDetailService;
import com.alex.erp.basic.ConfigurationProperties;
import com.alex.erp.basic.dic.SystemConfig;
import com.alex.erp.basic.vo.Result;
import com.alex.erp.basic.vo.factory.ResultFactory;
import com.alex.erp.basic.web.HttpClientUtil;
import com.alex.erp.authorization.service.MyUserDetailService;
import com.alex.erp.authorization.service.AuthorizeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

/**
 * 〈会员Controller〉
 *
 * @author Alex ZY Liang
 * @create 2018/12/13
 * @since 1.0.0
 */
@RestController
//@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private AuthorizeService authorizeService;

    @GetMapping("/member")
    public Principal user(Principal member) {
        //获取当前用户信息
        return member;
    }


    @PostMapping(value = "/appLogin")
    @ResponseBody
//    public Result authrize(String grant_type,String client_id,String client_secret,String username,String password,String redirect_uri) {

    public Result authrize(@RequestBody Map object) {

        try {
            return HttpClientUtil.sendPostDataByMap(
                    ConfigurationProperties.authrizeURL,
                    object,
                    SystemConfig.UTF8);
        } catch (IOException e) {
            return ResultFactory.buildFailResult("无法连接认证服务器");
        }
//    public Result authrize(@RequestBody LogVo logVo) {
//        Result result =  authorizeService.authorize(logVo);
//        return result;
    }

    @DeleteMapping(value = "/exit")
    public Result revokeToken(String access_token) {
        //注销当前用户
        Result result = new Result();
        if (consumerTokenServices.revokeToken(access_token)) {
            ResultFactory.buildSuccessResult("注销成功", null);
        } else {
            ResultFactory.buildFailResult("注销失败");
        }
        return result;
    }
}
