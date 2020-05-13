package com.yuansb.hello.spring.boot.config;


import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 开启spring session支持
 * @EnableRedisHttpSession这个注解是由spring-session-data-redis提供的
 * @EnableRedisHttpSession注解其中maxInactiveIntervalInSeconds参数是设置Session失效时间
 * @EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)	//spring在多长时间后强制使redis中的session失效,默认是1800.(单位/秒)
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig extends CachingConfigurerSupport {
}
