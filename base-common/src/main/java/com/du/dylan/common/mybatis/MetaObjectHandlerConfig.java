package com.du.dylan.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.du.dylan.common.constants.Constant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("metaObjectHandlerConfig")
public class MetaObjectHandlerConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 设置在新增的时候录入删除字段
        setFieldValByName("deleteStatus", Constant.not_delete_value,metaObject);
        // 设置在新增的时候录入创建时间
        setFieldValByName("createTime", new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置在修改的时候录入修改时间
        setFieldValByName("modifyTime", new Date(),metaObject);
    }
}
