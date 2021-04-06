package com.du.dylan.basedao.entity;

import com.du.dylan.common.mybatis.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author dylan
 * @since 2021-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 跳转方式
     */
    private String target;

    /**
     * 跳转链接
     */
    private String href;

    /**
     * 上级菜单
     */
    private Integer parentId;

    /**
     * 备注
     */
    private String remark;


}
