package cn.alex.demosplit.serviceum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ServiceUmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUmApplication.class, args);
    }

}
