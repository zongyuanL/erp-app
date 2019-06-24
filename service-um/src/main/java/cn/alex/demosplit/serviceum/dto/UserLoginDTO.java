package cn.alex.demosplit.serviceum.dto;

import lombok.Data;

@Data
public class UserLoginDTO {

    private JWT jwt;
    private User user;
}
