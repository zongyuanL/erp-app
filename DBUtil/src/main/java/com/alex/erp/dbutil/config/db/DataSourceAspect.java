package com.alex.erp.dbutil.config.db;

import com.alex.erp.dbutil.config.db.annotation.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-22 4:17 PM
 */
//@Component
@Slf4j
//@Aspect
//@Order(-1)
public class DataSourceAspect {

    @Pointcut("@within(com.alex.erp.dbutil.config.db.annotation.DataSource) ||" +
            " @annotation(com.alex.erp.dbutil.config.db.annotation.DataSource)")
    public void pointCut(){

    }

    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource){
        log.info("选择数据源---"+dataSource.value().getValue());
        DataSourceContextHolder.setDataSource(dataSource.value().getValue());
    }

    @After("pointCut()")
    public void doAfter(){
        DataSourceContextHolder.clear();
    }
}