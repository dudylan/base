package com.du.dylan.multipledatasource.datasource;

import java.lang.annotation.*;

/**
 * Multiple DataSource Aspect For Switch DataSource
 *
 * @author HelloWood
 * @date 2017-08-15 14:36
 * @Email hellowoodes@gmail.com
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}
