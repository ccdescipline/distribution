package com.distribution.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.distribution")
@MapperScan("com.distribution.common.mapper")
@EnableTransactionManagement
public class distributionApplication {
    public static void main(String[] args) {
        SpringApplication.run(distributionApplication.class, args);
    }
}
