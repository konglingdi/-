package com.kld.muldsdemo.pojo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @ClassName DataSourceContextHolder
 * @date: 2020.09.05 09:48
 * @Author: 孔令迪
 */
@Slf4j
public class DataSourceContextHolder {
    private static ThreadLocal<String> dataSource = new ThreadLocal<>();

    public static void setDataSource(String dataSourceBeanName){
        log.info("设置当前数据源为：{}", dataSourceBeanName);
        dataSource.set(dataSourceBeanName);
    }

    public static void removeDataSource(){
        log.info("移除当前数据源");
        dataSource.remove();
    }

    public static String get(){
        return dataSource.get();
    }
}
