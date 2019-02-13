package com.travelblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Configuration
public class Config {

    @Bean
    public BCrypt bCrypt() {
        return new BCrypt();
    }

}
