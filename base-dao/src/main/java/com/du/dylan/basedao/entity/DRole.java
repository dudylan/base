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
public class DRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String roleName;


}
