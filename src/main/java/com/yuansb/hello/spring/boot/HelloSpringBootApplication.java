package com.yuansb.hello.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.yuansb.hello.spring.boot.mapper")
//排除DataSource自动配置类,否则会默认自动配置,不会使用我们自定义的DataSource,并且启动报错
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HelloSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBootApplication.class, args);
    }

}
