package com.alex.erp.basic.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import javax.servlet.http.HttpServletResponse;

//@Configuration
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${auth.clientID}")
    private String clientID;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @Value("${auth.checkTokenEndpointUrl}")
    private String checkTokenEndpointUrl;

    @Primary
    @Bean
    public ResourceServerTokenServices tokenService() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setClientId("testclient");
//        tokenServices.setClientSecret("123456");
//        tokenServices.setCheckTokenEndpointUrl("http://localhost:9999/auth/oauth/check_token");
        tokenServices.setClientId(this.clientID);
        tokenServices.setClientSecret(this.clientSecret);
        tokenServices.setCheckTokenEndpointUrl(this.checkTokenEndpointUrl);
        return tokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}