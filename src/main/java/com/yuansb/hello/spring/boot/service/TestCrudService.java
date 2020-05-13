package com.yuansb.hello.spring.boot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuansb.hello.spring.boot.domain.TestDbInfo;
import com.yuansb.hello.spring.boot.mapper.TestDbInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TestCrudService {

    @Autowired
    private TestDbInfoMapper testDbInfoMapper;

    /**
     * 插入
     * @return
     */
    public int insert() {
        TestDbInfo testDbInfo = new TestDbInfo();
        testDbInfo.setId(10);
        testDbInfo.setName("测试10");
        return testDbInfoMapper.insert(testDbInfo);
    }

    /**
     * 更新
     * @param id
     * @return
     */
    public int update(Integer id) {
        TestDbInfo testDbInfo = testDbInfoMapper.selectByPrimaryKey(id);
        testDbInfo.setName("测试10Up");
        return testDbInfoMapper.updateByPrimaryKey(testDbInfo);
    }

    /**
     * 查询全部
     * @return
     */
    public List<TestDbInfo> selectAll() {
        List<TestDbInfo> lists = testDbInfoMapper.selectAll();
        if (lists != null && lists.size() > 0) {
            return lists;
        }
        return null;
    }

    /**
     * 条件查询
     * @param name
     * @return
     */
    public List<TestDbInfo> selectCondition(String name) {
        Example example = new Example(TestDbInfo.class);
        example.createCriteria().andEqualTo("name", name);
        List<TestDbInfo> lists = testDbInfoMapper.selectByExample(example);
        if (lists != null && lists.size() > 0) {
            return lists;
        }
        return null;
    }

    /**
     * 条件查询分页
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<TestDbInfo> selectPageCondition(String name, Integer pageNum, Integer pageSize) {
        Example example = new Example(TestDbInfo.class);
        example.createCriteria().andEqualTo("name", name);

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<TestDbInfo> pageInfo = new PageInfo<>(testDbInfoMapper.selectByExample(example));
        System.out.println(pageInfo);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        List<TestDbInfo> lists = pageInfo.getList();
        if (lists != null && lists.size() > 0) {
            return lists;
        }
        return null;
    }

}
