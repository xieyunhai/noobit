package com.example.querydsl.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author admin
 * @since 2017/9/18 15:24
 */
@Configuration
public class DruidDataSourceConfig {
    /**
     * DataSource 配置
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource.druid.read")
    @Qualifier("readDruidDataSource")
    @Bean(name = "readDruidDataSource")
    @Primary
    public DataSource readDruidDataSource() {
        return new DruidDataSource();
    }


    /**
     * DataSource 配置
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource.druid.write")
    @Qualifier("writeDruidDataSource")
    @Bean(name = "writeDruidDataSource")
    public DataSource writeDruidDataSource() {
        return new DruidDataSource();
    }
}
