package com.du.dylan.basedynamic.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.du.dylan.common.mybatis.MetaObjectHandlerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfigurer {

    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }




    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicRoutingDataSource = new DynamicDataSource();
        dynamicRoutingDataSource.setDefaultDataSource(master());
        return dynamicRoutingDataSource;

    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis-plus")
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        //由于使用了自定义数据源， 故一些注解形式的自动配置将不可用， 所以这里要手动配置
        GlobalConfig globalConfig=new GlobalConfig();
        // 设置自动更新  修改时间 创建时间
        globalConfig.setMetaObjectHandler(new MetaObjectHandlerConfig());
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        return sqlSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
