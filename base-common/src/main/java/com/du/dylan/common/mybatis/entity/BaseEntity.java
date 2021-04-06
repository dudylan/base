package com.du.dylan.common.mybatis.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date modifyTime;



    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    /**
     * 删除标示
     */
    @TableLogic(value = "false", delval = "true")
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleteStatus;


}
