package com.yuansb.hello.spring.boot.controller;

import com.yuansb.hello.spring.boot.service.TestDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestDataSourceController {

    @Autowired
    private TestDataSourceService testDataSourceService;

    @GetMapping("/test/ds/inDs1Deal")
    public void testInDs1DealDb(HttpServletRequest request) {
        testDataSourceService.inDs1DealDb();
    }

    @GetMapping("/test/ds/inDs2Deal")
    public void testInDs2DealDb(HttpServletRequest request) {
        testDataSourceService.inDs2DealDb();
    }

    @GetMapping("/test/ds/inDs2AspectDeal")
    public void testInDs2AspectDealDb(HttpServletRequest request) {
        testDataSourceService.inDs2AspectDealDb();
    }

}
