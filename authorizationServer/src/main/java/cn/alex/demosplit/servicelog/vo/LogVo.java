package cn.alex.demosplit.servicelog.vo;

import lombok.Data;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-06-26 6:08 PM
 */
@Data
public class LogVo {

    private String client_id;
    private String client_secret;
    private String grant_type;
    private String username;
    private String password;
    private String redirect_uri;

}
