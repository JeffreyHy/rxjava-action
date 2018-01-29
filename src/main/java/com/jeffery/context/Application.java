package com.jeffery.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan(basePackages = {"com.jeffery"})
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(Application.class, args);
        logger.info("------design-pattern-action-core start success!!!---------");
    }
}