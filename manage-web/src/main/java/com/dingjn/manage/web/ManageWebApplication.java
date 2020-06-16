package com.dingjn.manage.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 项目启动类.
 */
@ComponentScan(basePackages = "com.dingjn")   //扫描指定包，因为是多moudle开发，所以需要指定才能扫描其他moudle
@MapperScan(basePackages = {"com.dingjn.**.mapper"}) //扫描Mapper **代表中间不管隔多少包
@SpringBootApplication
public class ManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageWebApplication.class, args);
    }

}
