package com.alex.erp.authorization;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.alex.erp.dbutil.um","com.alex.erp.authorization"})
@EnableFeignClients
@EnableDiscoveryClient
//@MapperScan("cn.alex.demosplit.servicelog.dao")
@MapperScan("com.alex.erp.dbutil.um.mapper")
public class ServiceLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLogApplication.class, args);
    }
}
