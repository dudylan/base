package com.du.dylan.multipledatasource.datasource;


import com.du.dylan.common.spring.SpringUtil;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {


    /**
     * Maintain variable for every thread, to avoid effect other thread
     */
    private static final ThreadLocal<DataSource> contextHolder = ThreadLocal.withInitial(() -> (DataSource) SpringUtil.getBean("master"));

    /**
     * All DataSource List
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();

    /**
     * To switch DataSource
     *
     * @param key the key
     */
    public static void setDataSource(DataSource key) {
        contextHolder.set(key);
    }

    /**
     * Get current DataSource
     *
     * @return data source key
     */
    public static DataSource getDataSource() {
        return contextHolder.get();
    }

    /**
     * To set DataSource as default
     */
    public static void clearDataSource() {
        contextHolder.remove();
    }

    /**
     * Check if give DataSource is in current DataSource list
     *
     * @param key the key
     * @return boolean boolean
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }
}
