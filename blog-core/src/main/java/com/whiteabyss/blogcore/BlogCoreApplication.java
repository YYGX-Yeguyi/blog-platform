package com.whiteabyss.blogcore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.whiteabyss.blogcore.mapper")
@SpringBootApplication
public class BlogCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogCoreApplication.class, args);
    }

}
