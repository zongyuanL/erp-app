package cn.alex.demosplit.servicelog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.alex.erp.dbutil.um","cn.alex.demosplit.servicelog"})
@EnableDiscoveryClient
//@MapperScan("cn.alex.demosplit.servicelog.dao")
@MapperScan("com.alex.erp.dbutil.um.dao")
public class ServiceLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLogApplication.class, args);
    }
}
