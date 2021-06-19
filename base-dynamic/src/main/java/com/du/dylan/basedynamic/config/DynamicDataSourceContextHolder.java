package com.du.dylan.basedynamic.config;



import com.du.dylan.common.constants.Constant;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {


    /**
     * Maintain variable for every thread, to avoid effect other thread
     */
    private static final ThreadLocal<String> contextHolder = ThreadLocal.withInitial(() ->  Constant.DEFAULT_DATASOURCE_NAME);


    /**
     * To switch DataSource
     *
     * @param key the key
     */
    public static void setDataSourceKey(String key) {
        contextHolder.set(key);
    }

    /**
     * Get current DataSource
     *
     * @return data source key
     */
    public static String getDataSourceKey() {
        return contextHolder.get();
    }

    /**
     * To set DataSource as default
     */
    public static void clearDataSourceKey() {
        contextHolder.remove();
    }

}
