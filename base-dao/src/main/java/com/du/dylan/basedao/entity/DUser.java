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
public class DUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 角色id
     */
    private Integer roleId;

}
