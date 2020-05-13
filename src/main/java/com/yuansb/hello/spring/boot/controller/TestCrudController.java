package com.yuansb.hello.spring.boot.controller;

import com.yuansb.hello.spring.boot.domain.TestDbInfo;
import com.yuansb.hello.spring.boot.service.TestCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestCrudController {

    @Autowired
    private TestCrudService testCrudService;

    @GetMapping("/test/crud/insert")
    public int insert() {
        return testCrudService.insert();
    }

    @GetMapping("/test/crud/update/{id}")
    public int update(@PathVariable Integer id) {
        return testCrudService.update(id);
    }

    @GetMapping("/test/crud/selectAll")
    public List<TestDbInfo> selectAll() {
        return testCrudService.selectAll();
    }

    @GetMapping("/test/crud/selectCondition/{name}")
    public List<TestDbInfo> selectCondition(@PathVariable String name) {
        return testCrudService.selectCondition(name);
    }

    @GetMapping("/test/crud/selectPageCondition/{name}/{pageNum}/{pageSize}")
    public List<TestDbInfo> selectPageCondition(@PathVariable String name,
                                                @PathVariable Integer pageNum,
                                                @PathVariable Integer pageSize) {
        return testCrudService.selectPageCondition(name, pageNum, pageSize);
    }

}
