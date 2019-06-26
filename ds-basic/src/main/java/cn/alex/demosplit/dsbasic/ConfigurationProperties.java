package cn.alex.demosplit.dsbasic;

import org.springframework.beans.factory.annotation.Value;

/**
 * @version 0.0.1
 * @Description
 * @Author Rex
 * @Date 2019-06-25 4:31 PM
 */

public class ConfigurationProperties {

//    @Value("${authorization.tokenUri:}")
    public static String authrizeURL="http://localhost:9999/auth/oauth/token";
}
