package com.alex.erp.dbutil;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-04 2:57 PM
 */
//@Configuration
//@Data
//@ConfigurationProperties(prefix = "spring.datasource")
//@PropertySource("classpath:config/application.yml")
public class DataSourcePeoperties{
    private  String url;
    private  String username;
    private  String password;
}