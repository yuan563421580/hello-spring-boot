package com.yuansb.hello.spring.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestRedisSessionController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/test/redis/session/intoSession")
    public Map testIntoSession(HttpServletRequest request) {
        String info = "hello redis session ; i am from " + port;
        HttpSession session = request.getSession();
        session.setAttribute("info", info);

        Map<String, Object> map = new HashMap<>();
        map.put("info", info);
        return map;
    }

    @GetMapping("/test/redis/session/getSession")
    public Map testGetSession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("info", session.getAttribute("info"));
        return map;
    }
}
