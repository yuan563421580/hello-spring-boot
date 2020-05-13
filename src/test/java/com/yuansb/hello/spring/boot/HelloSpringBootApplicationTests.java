package com.yuansb.hello.spring.boot;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuansb.hello.spring.boot.domain.TestDbInfo;
import com.yuansb.hello.spring.boot.mapper.TestDbInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @SpringBootTest注解中有参数是webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
 * 该参数经常和测试类中@LocalServerPort在注入属性时搭配使用。目的是会随机生成一个端口号。
 *
 * 单元测试数据库的基本操作应该添加事务@Transactional和回滚@Rollback ； 测试通过但数据库不入数据
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback
public class HelloSpringBootApplicationTests {

    @Autowired
    private TestDbInfoMapper testDbInfoMapper;

    @Test
    public void contextLoads() {
        System.out.println("Hello Sprig Boot");
    }

    @Test
    public void testInsert() {
        TestDbInfo testDbInfo = new TestDbInfo();
        testDbInfo.setId(8);
        testDbInfo.setName("测试");
        testDbInfoMapper.insert(testDbInfo);
    }

    @Test
    public void testSelectAll() {
        List<TestDbInfo> testDbInfos = testDbInfoMapper.selectAll();
        testDbInfos.forEach(TestDbInfo -> {
            System.out.println(TestDbInfo);
        });
    }

    @Test
    public void testUpdate() {
        TestDbInfo testDbInfo= testDbInfoMapper.selectByPrimaryKey(6);
        testDbInfo.setName("你好");
        testDbInfoMapper.updateByPrimaryKey(testDbInfo);
    }

    @Test
    public void testDelete() {
        testDbInfoMapper.deleteByPrimaryKey(5);
    }

    @Test
    public void testPage() {
        PageHelper.startPage(1, 2);
        PageInfo<TestDbInfo> pageInfo = new PageInfo<>(testDbInfoMapper.selectAll());
        System.out.println(pageInfo);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        pageInfo.getList().forEach(testDbInfo -> {
            System.out.println(testDbInfo);
        });
    }

}
