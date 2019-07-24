package com.alex.erp.wechat.config;


import com.alex.erp.basic.baseconfig.auth.BasicResourceServerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
public class ResourceConfig extends BasicResourceServerConfig {

}
