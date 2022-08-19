package com.test.demo;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.test.demo.dao.mapper")
@Slf4j
public class DemoApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(DemoApplication.class);
        } catch (Exception e) {
            log.error("spring boot error cause: {}", Throwables.getStackTraceAsString(e));
        }
    }
}
