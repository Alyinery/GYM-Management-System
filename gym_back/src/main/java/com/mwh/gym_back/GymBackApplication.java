package com.mwh.gym_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mwh.gym_back.mapper")
public class GymBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymBackApplication.class, args);
    }

}
