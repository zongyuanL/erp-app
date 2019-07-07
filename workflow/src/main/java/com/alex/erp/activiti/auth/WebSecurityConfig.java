package com.alex.erp.activiti.auth;

import com.alex.erp.basic.baseconfig.auth.BaseSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@EnableOAuth2Sso
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends BaseSecurityConfig {

}
