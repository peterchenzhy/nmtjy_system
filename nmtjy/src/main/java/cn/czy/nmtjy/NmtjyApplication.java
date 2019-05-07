package cn.czy.nmtjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class NmtjyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NmtjyApplication.class, args);
    }

}

