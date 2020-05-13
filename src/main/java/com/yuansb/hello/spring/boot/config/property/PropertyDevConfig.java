package com.yuansb.hello.spring.boot.config.property;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Profile("dev")
@Configuration
@PropertySource(value = "classpath:conf/boot-dev.properties",ignoreResourceNotFound = true,encoding = "UTF-8")
public class PropertyDevConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

}
