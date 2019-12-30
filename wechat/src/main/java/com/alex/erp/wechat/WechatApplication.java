package com.alex.erp.wechat;


import com.alex.erp.wechat.config.TokenConfig;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication(scanBasePackages = {"com.alex.erp.**"},
        exclude={SecurityAutoConfiguration.class,
                DataSourceAutoConfiguration.class,
                DruidDataSourceAutoConfigure.class})
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableWebSecurity
@MapperScan("com.alex.erp.dbutil.**.mapper")
public class WechatApplication {

    public static void main(String[] args) {

        SpringApplication.run(WechatApplication.class, args);
        TokenConfig tokenConfig = new TokenConfig();
    }
    @Bean
	public RequestContextListener requestContextListener() {
	    return new RequestContextListener();
	}
}


//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1a1bf2182af25af1&redirect_uri=http%3A%2F%2Fyq5447.natappfree.cc%2Fwechat%2Fapi%2FgetCode&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
