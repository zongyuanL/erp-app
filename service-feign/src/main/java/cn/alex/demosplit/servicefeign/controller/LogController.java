package cn.alex.demosplit.servicefeign.controller;

import cn.alex.demosplit.dsbasic.vo.LoginInfoVo;
import cn.alex.demosplit.dsbasic.vo.Result;
import cn.alex.demosplit.dsbasic.vo.factory.ResultFactory;
import cn.alex.demosplit.servicefeign.feignInterface.LogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Log4j2
public class LogController {

    @Autowired
    LogService logService;

    /**
     * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
     * 给VueLoginInfoVo对象加入@Valid注解，并在参数中加入BindingResult来获取错误信息。
     * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
     */
//    @CrossOrigin
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
//            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
//    })
    @ApiOperation(value="用户登录",notes = "根据用户名称，密码认证", httpMethod = "POST")
    @ApiImplicitParam(name="loginInfoVo",value="用户信息", required = true,dataType = "LoginInfoVo")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@Valid @RequestBody LoginInfoVo loginInfoVo, BindingResult bindingResult) {
        log.info("********************"+loginInfoVo.getUsername());

        if (bindingResult.hasErrors()) {
            String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }else{
            return logService.logFromClientOne(loginInfoVo);
        }

    }
}
