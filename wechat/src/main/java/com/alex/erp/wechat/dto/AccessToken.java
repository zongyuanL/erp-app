package com.alex.erp.wechat.dto;

import lombok.Data;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-13 12:46 AM
 */

@Data
public class AccessToken {

    //获取到的凭证
    private String accessToken;
    //凭证有效时间，单位：秒
    private int expiresin;


}
