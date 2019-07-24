package com.alex.erp.dbutil.config.mybatisplus;

//import com.baomidou.mybatisplus.core.injector.ISqlInjector;
//import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

import com.alex.erp.dbutil.config.db.DataSourceEnum;
import com.alex.erp.dbutil.config.db.MultipleDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-03 9:26 AM
 */

//@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    /*
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
//        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

//    @Bean(name = "db_um")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.druid.um" )
//    public DataSource db_um() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "db_eva")
//    @ConfigurationProperties(prefix = "spring.druid.eva" )
//    public DataSource db_eva() {
//        return DruidDataSourceBuilder.create().build();
//    }


    /**
     * 动态数据源配置
     * @return
     */
//    @Bean
//    @Primary
//    public DataSource multipleDataSource(@Qualifier("db_eva") DataSource db_eva, @Qualifier("db_um") DataSource db_um) {
//        MultipleDataSource multipleDataSource = new MultipleDataSource();
//        Map< Object, Object > targetDataSources = new HashMap<>();
//        targetDataSources.put(DataSourceEnum.DB_EVALUATION.getValue(), db_eva);
//        targetDataSources.put(DataSourceEnum.DB_UM.getValue(), db_um);
//        //添加数据源
//        multipleDataSource.setTargetDataSources(targetDataSources);
//        //设置默认数据源
//        multipleDataSource.setDefaultTargetDataSource(db_eva);
//        return multipleDataSource;
//    }

//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(multipleDataSource(db_eva(),db_um()));
//        //sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*Mapper.xml"));
//
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        //configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setCacheEnabled(false);
//        sqlSessionFactory.setConfiguration(configuration);
//        sqlSessionFactory.setPlugins(
//                //PerformanceInterceptor(),OptimisticLockerInterceptor()
//                new Interceptor[]{
//                        //添加分页功能
//                paginationInterceptor()
//        });
//        //sqlSessionFactory.setGlobalConfig(globalConfiguration());
//        return sqlSessionFactory.getObject();
//    }
}
