package com.yuansb.hello.spring.boot.service;

import com.yuansb.hello.spring.boot.annotation.DataSourceSign;
import com.yuansb.hello.spring.boot.common.ContextConst;
import com.yuansb.hello.spring.boot.dataSource.DataSourceContextHolder;
import com.yuansb.hello.spring.boot.domain.TestDbInfo;
import com.yuansb.hello.spring.boot.mapper.TestDbInfoMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TestDataSourceService {

    @Autowired
    private TestDbInfoMapper testDbInfoMapper;

    /**
     * 测试连接数据库 数据源DS_1
     */
    public void inDs1DealDb() {
        log.info("TestService.inDs1DealDb ... ");
        TestDbInfo testDbInfo = testDbInfoMapper.selectByPrimaryKey(1);
        log.info("inDs1DealDb 获取的内容: " + testDbInfo.getName());
    }

    /**
     * 测试连接数据库 数据源DS_2
     */
    public void inDs2DealDb() {
        try {
            log.info("TestService.inDs2DealDb ... ");
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.DS_2.name());
            TestDbInfo testDbInfo = testDbInfoMapper.selectByPrimaryKey(1);
            log.info("inDs2DealDb 获取的内容: " + testDbInfo.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.DS_1.name());
        }
    }

    /**
     * 测试连接数据库 数据源DS_2
     */
    @DataSourceSign(ContextConst.DataSourceType.DS_2)
    public void inDs2AspectDealDb() {
        log.info("TestService.inDs2AspectDealDb ... ");
        TestDbInfo testDbInfo = testDbInfoMapper.selectByPrimaryKey(1);
        log.info("inDs2AspectDealDb 获取的内容: " + testDbInfo.getName());
    }

}
