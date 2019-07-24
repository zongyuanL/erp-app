package com.alex.erp.dbutil.config.db;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-22 6:06 PM
 */
//@Component
//@Order(value = -100)
@Slf4j
//@Aspect
public class DataSourceSwitchAspect {
    @Pointcut("execution(* com.alex.erp.dbutil.um..*.*(..))")
        private void umAspect() {
    }

//    @Pointcut("execution(* com.df.openapi.*.mapper.db2..*.*(..))")
//    private void db2Aspect() {
//    }



    @Before("umAspect()")
    public void db1() {
        log.info("切换到UM 数据源...");
        DataSourceContextHolder.setDataSource(DataSourceEnum.DB_UM.getValue());
    }
}
