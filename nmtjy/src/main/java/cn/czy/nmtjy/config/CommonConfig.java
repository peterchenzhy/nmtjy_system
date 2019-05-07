package cn.czy.nmtjy.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CommonConfig
 *
 * @author PeterChen
 * @summary CommonConfig
 * @Copyright (c) 2019, Lianjia Group All Rights Reserved.
 * @Description CommonConfig
 * @since 2019-04-14 12:41
 */
@Configuration
public class CommonConfig {
    @Bean
    PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
