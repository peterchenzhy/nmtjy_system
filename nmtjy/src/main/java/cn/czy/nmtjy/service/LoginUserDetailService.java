package cn.czy.nmtjy.service;

import cn.czy.nmtjy.model.po.EmployeePo;
import cn.czy.nmtjy.service.employee.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: PeterChen
 * @Date: 2019/2/10 10:34
 * @Version 1.0
 */
@Component
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查找用户
        EmployeePo po = employeeService.queryEmployeeByName(username);
        if(po==null){
            throw new UsernameNotFoundException (String.format("用户名：%s 不存在",username));
        }
        String password = po.getPassword();

        //获取权限点列表
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
        List<GrantedAuthority> authorities = new ArrayList();
        authorities.add(authority);

        return new User(username,password, authorities);

    }
}
