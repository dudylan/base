package com.du.dylan.basedynamic.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
public class TemplateDataSource  {

    @ConfigurationProperties(prefix = "spring.datasource.template")
    @Scope("prototype")
    @Bean("templateCreateDataSource")
    public DruidDataSource createDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
