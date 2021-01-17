package com.yrc.aisservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
@MapperScan("com.yrc.aisservice.mapper")
public class AisserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AisserviceApplication.class, args);
    }

}
