package cn.czy.nmtjy.config;

import cn.czy.nmtjy.commons.Consts;
import cn.czy.nmtjy.commons.NmtjyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: PeterChen
 * @Date: 2019/2/21 22:26
 * @Version 1.0
 */
public class MyJWTAuthenticationFilter extends BasicAuthenticationFilter {

    public MyJWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(Consts.HTTP_HEADER_TOKEN);

        if(token==null || !token.startsWith(Consts.HTTP_HEADER_BEARER)){
            chain.doFilter(request,response);
            return ;
        }
        UsernamePasswordAuthenticationToken authenticationToken = this.checkUsernamePasswordAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //进入下一个filter
        chain.doFilter(request,response);
    }


    /**
    * 验签
    * @author PeterChen
    * @modifier PeterChen
    * @version v1
    * @since 2019/2/24 13:04
    * @param token
    * @summary
    * @return  org.springframework.security.authentication.UsernamePasswordAuthenticationToken
    */
    private UsernamePasswordAuthenticationToken checkUsernamePasswordAuthenticationToken(String token){

        Claims claims = Jwts.parser().setSigningKey(Consts.JWT_KEY)
                .parseClaimsJws(token.replace(Consts.HTTP_HEADER_BEARER,""))
                .getBody();
        String username = claims.getSubject();
        if(System.currentTimeMillis()>claims.getExpiration().getTime()){
            throw new NmtjyException("该账号已过期，请重新登录");
        }
        if(username !=null){
            return new UsernamePasswordAuthenticationToken(username,null,new ArrayList<>());
        }
        throw new NmtjyException("该签名无效，请重新登录");

    }
}
