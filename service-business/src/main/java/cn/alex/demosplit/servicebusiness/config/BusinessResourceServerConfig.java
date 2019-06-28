package cn.alex.demosplit.servicebusiness.config;

import com.alex.erp.basic.auth.ResourceServerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
public class BusinessResourceServerConfig extends ResourceServerConfig {

}