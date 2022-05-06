package com.guang.yuantodo;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.guang.yuantodo.mapper")
public class YuanTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuanTodoApplication.class, args);
    }

}
