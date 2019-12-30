package com.alex.erp.basic.baseconfig.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @Title: TokenFeignClientInterceptor.java
 * @Package com.deec.evaluation.feign.auth
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 熊勇奇
 * @date 2019年8月9日 下午3:38:23
 * @version V1.0
 */
@Configuration
public class TokenFeignClientInterceptor implements RequestInterceptor {

    /**
     * token放在请求头.
     *
     * @param requestTemplate 请求参数
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if(authentication!=null&&authentication.getDetails() instanceof OAuth2AuthenticationDetails){
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
            requestTemplate.header("Authorization", String.format("%s %s", "bearer",details.getTokenValue()));
        }
    }
}