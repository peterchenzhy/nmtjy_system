package cn.czy.nmtjy.config;

import cn.czy.nmtjy.service.LoginUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
//        http.formLogin().failureForwardUrl("/login?error").permitAll();
        http.formLogin().failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }).successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setStatus(HttpStatus.ACCEPTED.value());
            }
        }).permitAll();
        http.logout().permitAll();
        http.csrf().disable();
        http.anonymous().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUserDetailService).passwordEncoder(passwordEncoderConfig);
//        auth.authenticationProvider(myAuthenticationProvider).userDetailsService(loginUserDetailService).passwordEncoder(passwordEncoderConfig);
        super.configure(auth);
    }


}
