package com.hezb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hezb.mapper")
public class VideoPlatFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoPlatFormApplication.class, args);
    }
}
