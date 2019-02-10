package cn.czy.nmtjy.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义密码加密器 临时
 * @Author: PeterChen
 * @Date: 2019/2/10 15:43
 * @Version 1.0
 */
@Component
public class MyPasswordEncoderConfig implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}
