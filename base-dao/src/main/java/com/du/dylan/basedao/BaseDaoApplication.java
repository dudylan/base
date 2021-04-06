package com.du.dylan.basedao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.du.dylan")
public class BaseDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseDaoApplication.class, args);
    }

}
