package com.kld.muldsdemo.pojo;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description:
 * @ClassName RoutingDataSource
 * @date: 2020.09.05 09:47
 * @Author: 孔令迪
 */
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }
}
