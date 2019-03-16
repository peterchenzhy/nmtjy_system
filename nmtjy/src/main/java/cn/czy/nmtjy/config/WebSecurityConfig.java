package cn.czy.nmtjy.config;

import cn.czy.nmtjy.service.LoginUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * webSecurityConfig
 *
 * @Author: PeterChen
 * @Date: 2019/2/10 10:24
 * @Version 1.0
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginUserDetailService loginUserDetailService;
    @Autowired
    private MyPasswordEncoderConfig passwordEncoderConfig;
    @Autowired
    private MyLoginAuthenticationProvider myAuthenticationProvider;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/test1").permitAll()
                .antMatchers("/test/cache").permitAll()
                .anyRequest().authenticated()
                .and()
                //验证登录
                .addFilter(new MyJWTLoginFilter(authenticationManagerBean()))
                //验证token
                .addFilter(new MyJWTAuthenticationFilter(authenticationManagerBean()));

                http.httpBasic();
                http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);
        http.logout().permitAll();
        http.anonymous().disable();



//        http.formLogin().failureHandler(new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            }
//        }).successHandler(new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                response.setStatus(HttpStatus.ACCEPTED.value());
//            }
//        }).permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(loginUserDetailService).passwordEncoder(passwordEncoderConfig);
//        auth.authenticationProvider(myAuthenticationProvider).userDetailsService(loginUserDetailService).passwordEncoder(passwordEncoderConfig);
        auth.authenticationProvider(myAuthenticationProvider);
//        super.configure(auth);
    }


}
