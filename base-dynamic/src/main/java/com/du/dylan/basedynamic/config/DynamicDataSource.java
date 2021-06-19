package com.du.dylan.basedynamic.config;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.du.dylan.common.constants.Constant;
import com.du.dylan.common.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;


/**
 * @author dylan
 *  通过继承 AbstractRoutingDataSource 来实现数据源的管理
 *  动态数据源的信息可以通过数据库获取  这里只切换数据库 所以把基础的数据库信息配置到 配置文件中
 *  dynamicDBuser
 *  dynamicDBurl
 *  dynamicDBpass
* */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    // 默认数据源，也就是主库
    protected DataSource defaultDataSource;

    // 保存动态创建的数据源
    private static final Map<String, DataSource> targetDataSource = new ConcurrentHashMap<>();

    @Override
    protected DataSource determineTargetDataSource() {
        // 根据数据库选择方案，拿到要访问的数据库
        String dataSourceName = determineCurrentLookupKey();
        if(Constant.DEFAULT_DATASOURCE_NAME.equals(dataSourceName)) {
            // 访问默认主库
            return defaultDataSource;
        }

        // 根据数据库名字，从已创建的数据库中获取要访问的数据库
        DataSource dataSource = targetDataSource.get(dataSourceName);
        if(null == dataSource) {
            // 从已创建的数据库中获取要访问的数据库，如果没有则创建一个
            try {
                dataSource = this.selectDataSource(dataSourceName);
            } catch (SystemException e) {
                log.error("选择数据源异常{}",e);
            }
        }
        return dataSource;
    }

    @Override
    protected String determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceKey();
        if (dataSourceName == null || dataSourceName.equals(Constant.DEFAULT_DATASOURCE_NAME)) {
            // 默认的数据源名字
            dataSourceName =  Constant.DEFAULT_DATASOURCE_NAME;
        }
        log.debug("use datasource : {}",dataSourceName);
        return dataSourceName;
    }


    public void addTargetDataSource(String key, DruidDataSource dataSource) {
        targetDataSource.put(key, dataSource);
    }


    /**
     * 该方法为同步方法，防止并发创建两个相同的数据库
     * 使用双检锁的方式，防止并发
     * @param dbType
     * @return
     * @throws SystemException
     */
    private synchronized DataSource selectDataSource(String dbType){
        // 再次从数据库中获取，双检锁
        DataSource obj = targetDataSource.get(dbType);
        if (null != obj) {
            return obj;
        }
        // 为空则创建数据库
        DruidDataSource dataSource = this.getDataSource(dbType);
        // 将新创建的数据库保存到map中
        this.setDataSource(dbType, dataSource);
        return dataSource;
    }

    /**
     * 查询对应数据库的信息
     * @param dbtype
     * @return
     */
    private DruidDataSource getDataSource(String dbtype) {
        //String oriType = DynamicDataSourceContextHolder.getDataSourceKey();
        // 先切换回主库
        //DynamicDataSourceContextHolder.setDataSourceKey("default");
        // 查询所需信息
        //  CenterDatebase datebase = centerDatebaseManager.getById(dbtype);
        //   Client  client = clientMapper.selectByDBName(dbtype);
        // 切换回目标库
        DynamicDataSourceContextHolder.setDataSourceKey(dbtype);
        DruidDataSource d = (DruidDataSource) SpringUtil.getBean("templateCreateDataSource");
        d.setUrl(d.getUrl().replace("${databaseName}",dbtype));
        return d;
    }


    public void setDataSource(String type, DruidDataSource dataSource) {
        this.addTargetDataSource(type, dataSource);
        DynamicDataSourceContextHolder.setDataSourceKey(type);
    }

/*    @Override
    public void setTargetDataSources(Map targetDataSources) {

        super.setTargetDataSources(targetDataSources);
        // 重点：通知container容器数据源发生了变化
        afterPropertiesSet();
    }*/

    /**
     * 该方法重写为空，因为AbstractRoutingDataSource类中会通过此方法将，targetDataSources变量中保存的数据源交给resolvedDefaultDataSource变量
     * 在本方案中动态创建的数据源保存在了本类的targetDataSource变量中。如果不重写该方法为空，会因为targetDataSources变量为空报错
     * 如果仍然想要使用AbstractRoutingDataSource类中的变量保存数据源，则需要在每次数据源变更时，调用此方法来为resolvedDefaultDataSource变量更新
     */
    @Override
    public void afterPropertiesSet() {

    }


    public void setDefaultDataSource(DataSource defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }
}
