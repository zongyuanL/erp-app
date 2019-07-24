package com.alex.erp.wechat;

import com.alex.erp.wechat.config.TokenConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class WechatApplication {

    public static void main(String[] args) {

        SpringApplication.run(WechatApplication.class, args);
        TokenConfig tokenConfig = new TokenConfig();
    }

}
