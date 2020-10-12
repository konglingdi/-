package com.kld.muldsdemo.config;

import com.kld.muldsdemo.pojo.RoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @ClassName DataSourceConfig2
 * @date: 2020.08.31 14:29
 * @Author: 孔令迪
 */
@Configuration
@MapperScan(basePackages = "com.kld.muldsdemo.mapper.db2", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DataSourceConfig2 {

    @Bean("db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource getDb1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("db3DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db3")
    public DataSource getDb2DataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean("db2SqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("routingDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/db2/*.xml"));
        return bean.getObject();
    }

    @Bean("db2SqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean("routingDataSource")
    public RoutingDataSource routingDataSource(){
        Map<Object, Object> dsMap = new HashMap<Object, Object>(){
            {
                put("db2DataSource",getDb1DataSource());
                put("db3DataSource",getDb2DataSource());
            }
        };

        RoutingDataSource dataSource = new RoutingDataSource();
        dataSource.setTargetDataSources(dsMap);
        return dataSource;
    }
}
