package com.springboot.log;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangliang
 */
@SpringBootApplication
@MapperScan("com.springboot.log.dao")
public class SpringbootLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootLogApplication.class, args);
    }
}
