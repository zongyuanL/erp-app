package com.alex.erp.wechat.config;

import com.alex.erp.basic.baseconfig.swagger.BasicSwagger2Config;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //必须存在
@EnableSwagger2 //必须存在
public class Swagger2 extends BasicSwagger2Config {

}
