package cn.alex.demosplit.dsbasic.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginInfoVo {

    @NotNull(message="用户名不允许为空")
    private String username;

    @NotNull(message="密码不允许为空")
    private String password;


}


