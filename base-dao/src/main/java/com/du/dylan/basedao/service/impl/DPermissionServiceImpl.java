package com.du.dylan.basedao.service.impl;

import com.du.dylan.basedao.entity.DPermission;
import com.du.dylan.basedao.mapper.DPermissionMapper;
import com.du.dylan.basedao.service.IDPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dylan
 * @since 2021-03-25
 */
@Service
@Transactional
public class DPermissionServiceImpl extends ServiceImpl<DPermissionMapper, DPermission> implements IDPermissionService {

}
