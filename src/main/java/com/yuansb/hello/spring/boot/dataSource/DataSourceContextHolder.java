package com.yuansb.hello.spring.boot.dataSource;

import lombok.extern.log4j.Log4j2;

/**
 * 数据源持有类,配合ThreadLocal存储数据源key
 * description:保存线程安全的数据源
 */
@Log4j2
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static void setDataSource(String dbType) {
        log.info("切换到[" + dbType + "]数据源");
        contextHolder.set(dbType);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }

}
