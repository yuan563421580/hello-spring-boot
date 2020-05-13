package com.yuansb.hello.spring.boot.controller;

import com.yuansb.hello.spring.boot.factory.PropertyFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class TestIndexController {

    @GetMapping(value = "/")
    private String testSayHello() {
        String info = "hello spring boot , current time is " + System.currentTimeMillis();
        log.info(info);
        return info;
    }

    @GetMapping(value = "/test/property/say")
    private String testPropertyInfo() {
        String info = "NowProfilesActive is " + PropertyFactory.getNowProfilesActive() + " , SayInfo is " + PropertyFactory.getSayInfo();
        return info;
    }

}
