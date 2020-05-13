package com.yuansb.hello.spring.boot.controller;

import com.yuansb.hello.spring.boot.utils.generateKey.RedisPrimaryKey;
import com.yuansb.hello.spring.boot.utils.generateKey.SnowflakeIdWorker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class TestGenerateKeyController {

    @Autowired
    private RedisPrimaryKey redisPrimaryKey;

    @GetMapping(value = "/test/generate/key/{type}")
    private String generatePrimaryKey(@PathVariable String type) {
        String primaryKey = "";
        if ("1".equalsIgnoreCase(type)) {
            primaryKey = redisPrimaryKey.generateKey("TEST_ORDER");
            log.info("generate key by redis success, the value is " + primaryKey);
        } else if ("2".equalsIgnoreCase(type)) {
            primaryKey = String.valueOf(SnowflakeIdWorker.generateId());
            log.info("generate key by snowflake success, the value is " + primaryKey);
        } else {
            log.info("i do't care this type");
        }
        return primaryKey;
    }


}
