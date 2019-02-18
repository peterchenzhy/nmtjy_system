package cn.czy.nmtjy.config;

import cn.czy.nmtjy.service.LoginUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * webSecurityConfig
 * @Author: PeterChen
 * @Date: 2019/2/10 10:24
 * @Version 1.0
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private LoginUserDetailService loginUserDetailService;
    @Autowired
    private MyPasswordEncoderConfig passwordEncoderConfig;
//    @Autowired
//    private MyAuthenticationProvider myAuthenticationProvider;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().failureForwardUrl("/login?error").permitAll();
        http.logout().permitAll();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUserDetailService).passwordEncoder(passwordEncoderConfig);
//        auth.authenticationProvider(myAuthenticationProvider).userDetailsService(loginUserDetailService).passwordEncoder(passwordEncoderConfig);
        super.configure(auth);
    }

}
