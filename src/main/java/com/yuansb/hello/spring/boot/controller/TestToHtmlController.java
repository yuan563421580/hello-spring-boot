package com.yuansb.hello.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestToHtmlController {

    @GetMapping(value = "/test/html/index")
    public String index(Model model) {
        model.addAttribute("info", "hello spring boot, i am the first html");
        return "index";
    }
}
