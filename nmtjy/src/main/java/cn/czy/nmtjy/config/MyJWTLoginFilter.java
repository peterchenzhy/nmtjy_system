package cn.czy.nmtjy.config;

import cn.czy.nmtjy.commons.Consts;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: PeterChen
 * @Date: 2019/2/21 21:07
 * @Version 1.0
 */
public class MyJWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public MyJWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username") == null ? "" : request.getParameter("username").trim();
        String password = request.getParameter("password") == null ? "" : request.getParameter("password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder().setSubject(authResult.getName())
                //有效期两个小时
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 2 * 1000))
                //采用算法 : HS512
                .signWith(SignatureAlgorithm.HS512, Consts.JWT_KEY).compact();
        response.addHeader(Consts.HTTP_HEADER_TOKEN, Consts.HTTP_HEADER_BEARER + token);
        response.addHeader(Consts.LONGIN_USER,authResult.getDetails().toString());
    }


}

