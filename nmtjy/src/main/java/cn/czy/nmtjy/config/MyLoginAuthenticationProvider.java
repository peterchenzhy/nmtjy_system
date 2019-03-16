package cn.czy.nmtjy.config;

import cn.czy.nmtjy.model.po.EmployeePo;
import cn.czy.nmtjy.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考 DaoAuthenticationProvider
 *
 * @Author: PeterChen
 * @Date: 2019/2/10 16:11
 * @Version 1.0
 */
@Component
public class MyLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        EmployeePo po = employeeService.queryEmployeeByName(username);
        if (po == null) {
            throw new AuthenticationServiceException("用户不存在");
        }
        if (!po.getPassword().equals(password)) {
            throw new AuthenticationServiceException("密码错误");
        }

        //获取权限点列表
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
        List<GrantedAuthority> authorities = new ArrayList();
        authorities.add(authority);
        UserDetails userDetails = new User(username, password, authorities);

        Authentication token = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        ((UsernamePasswordAuthenticationToken) token).setDetails(po.getId());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
