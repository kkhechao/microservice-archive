package com.zqkh.archive.context;

import com.jovezhao.nest.starter.EnableNest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@SpringBootApplication
@EnableEurekaClient
@EnableNest
@MapperScan("com.zqkh.archive.context.appservice.impl.domain.repository")
@EnableFeignClients(basePackages = {"com.zqkh.file.feign","com.zqkh.gene.feign"})
@ComponentScans({
        @ComponentScan("com.zqkh.archive.context"),
        @ComponentScan("com.zqkh.common.configuration")
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}