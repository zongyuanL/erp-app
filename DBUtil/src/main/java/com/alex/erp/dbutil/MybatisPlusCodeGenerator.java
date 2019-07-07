package com.alex.erp.dbutil;
//
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import lombok.Data;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-03 9:28 AM
 */
//@Configuration
//@PropertySource("classpath:config/application.yml")
public class MybatisPlusCodeGenerator {
//
////    @Value("${spring.datasource.url}")
////    private static String url;
////
////    @Value("${spring.datasource.username}")
////    private static String username;
////
////    @Value("${spring.datasource.password}")
////    private static String password;
////
////    @Value("${spring.datasource.driver-class-name}")
////    private static String driverClassName;
//
//    private static String url="jdbc:mysql://localhost:3306/member?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
//
//    private static String username="root";
//
//    private static String password="sa123";
//
//    private static String driverClassName="com.mysql.cj.jdbc.Driver";
//
//    private static String PARENT_PACKAGE_NAME="com.alex.erp.dbutil";
//
//    /**
//     如果模板引擎是 freemarker
//     **/
//    private static String templatePath = "/templates/mapper.xml.ftl";
//
//    /**
//     如果模板引擎是 velocity
//     **/
//    // private static String templatePath = "/templates/mapper.xml.vm";
//    /**
//     * velocity 模板引擎, 默认
//     * <dependency>
//     *     <groupId>org.apache.velocity</groupId>
//     *     <artifactId>velocity-engine-core</artifactId>
//     *     <version>最新版本</version>
//     * </dependency>
//     *
//     * // 切换为 freemarker 模板引擎
//     * mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//     *  freemarker 模板引擎
//     * <dependency>
//     *     <groupId>org.freemarker</groupId>
//     *     <artifactId>freemarker</artifactId>
//     *     <version>最新版本</version>
//     * </dependency>
//     */
//
//    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }
//
//
//
//    public static void main(String[] args) {
//
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/DBUtil/src/main/java");
//        gc.setAuthor("Alex ZY Liang");
//        gc.setOpen(false);
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl(url);
//        // dsc.setSchemaName("public");
//        dsc.setDriverName(driverClassName);
//        dsc.setUsername(username);
//        dsc.setPassword(password);
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名:"));
//        pc.setParent(PARENT_PACKAGE_NAME);
//        mpg.setPackageInfo(pc);
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//
//        // 如果模板引擎是 freemarker
////        String templatePath = "/templates/mapper.xml.ftl";
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名
//                return projectPath + "/DBUtil/src/main/resources/mybatis/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//
//        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//
//        // 配置自定义输出模板
//        // templateConfig.setEntity();
//        // templateConfig.setService();
//        // templateConfig.setController();
//
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.alex.erp.dbutil.base.BaseEntity");
//        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
////        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
//        strategy.setInclude(scanner("表名"));
//        strategy.setSuperEntityColumns("id");
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }
}
