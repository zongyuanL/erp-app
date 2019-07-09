package com.alex.erp.basic.baseconfig.auth;

import com.alex.erp.basic.dic.StaticParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-06-29 10:41 PM
 */
//@Configuration
//@EnableWebSecurity
//@Order(2)
//@EnableGlobalMethodSecurity(prePostEnabled =true)
public class BaseSecurityConfig  extends WebSecurityConfigurerAdapter {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        //return new BCryptPasswordEncoder();
//        return new NoEncryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers(
                        StaticParams.getIgnorePath()
                        //"/login","/login**","/appLogin","/oauth/**","/*.html"
                        ).permitAll()
                .and()
                .formLogin().permitAll()
                .and()
//                .formLogin()
//                .loginPage("/login_p")
//                .loginProcessingUrl("/login")
//                .usernameParameter("username").passwordParameter("password").permitAll()
//                .and()
                .csrf().disable();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
//    }

    /**
     * 不定义没有password grant_type,密码模式需要AuthenticationManager支持
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }
}
