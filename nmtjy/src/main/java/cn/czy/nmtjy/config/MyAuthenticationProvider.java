//package cn.czy.nmtjy.config;
//
//import cn.czy.nmtjy.service.LoginUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
///**
// * 参考 DaoAuthenticationProvider
// * @Author: PeterChen
// * @Date: 2019/2/10 16:11
// * @Version 1.0
// */
//@Component
//public class MyAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private LoginUserDetailService loginUserDetailService;
//
//    @Override
//    @Bean
//    @Lazy
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        UserDetails userDetails = loginUserDetailService.loadUserByUsername(authentication.getPrincipal().toString().trim());
//        return authentication;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
