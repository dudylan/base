package com.du.dylan.multipledatasource.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/***
*  考量切换数据源应该在线程开始时。所以更换到监听controller 方法来切换
* */
@Slf4j
public class DynamicDataSourceAspect {

    @Pointcut(value = "execution(* com.du.dylan.basedao.service.impl.DUserServiceImpl.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object invoke(ProceedingJoinPoint invocation) throws Throwable {
        log.info("方法执行之前");
        Object result = invocation.proceed();
        log.info("方法执行完毕");
        return result;
    }

    /**
     * Switch DataSource
     *
     * @param point
     * @param targetDataSource
     */
    @Before("pointcut()")
    public void switchDataSource(JoinPoint point) {
       /* DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/temp?allowPublicKeyRetrieval=true&useUnicode=true&autoReconnect=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("123456xxf");
        DynamicDataSourceContextHolder.setDataSource(dataSource);*/
       /* if (!DynamicDataSourceContextHolder.containDataSourceKey(targetDataSource.value())) {
            log.error("DataSource [{}] doesn't exist, use default DataSource [{}]", targetDataSource.value());
        } else {
            DynamicDataSourceContextHolder.setDataSourceKey(targetDataSource.value());
            log.info("Switch DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }*/
    }

    /**
     * Restore DataSource
     *
     * @param point
     * @param targetDataSource
     */
    @After("execution(* com.du.dylan.basedao.service.*.*(..))")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSource();
        log.info("Restore DataSource to [{}] in Method [{}]",
                "", point.getSignature());
    }

}
