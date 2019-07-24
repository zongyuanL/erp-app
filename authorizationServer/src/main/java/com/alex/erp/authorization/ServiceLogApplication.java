package com.alex.erp.authorization;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.alex.erp.dbutil.um","com.alex.erp.authorization"}
//, exclude = {DataSourceAutoConfiguration.class,DruidDataSourceAutoConfigure.class}
)
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.alex.erp.dbutil.um.mapper")
public class ServiceLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLogApplication.class, args);
    }
}
