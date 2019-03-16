package cn.czy.nmtjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NmtjyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NmtjyApplication.class, args);
    }

}

