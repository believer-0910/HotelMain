package com.exadel.demo.service.config;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Logger logger() {
        return org.slf4j.LoggerFactory.getLogger(Object.class);
    }

}
