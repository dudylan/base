package com.du.dylan.basedao.service.impl;

import com.du.dylan.basedao.entity.DRole;
import com.du.dylan.basedao.mapper.DRoleMapper;
import com.du.dylan.basedao.service.IDRoleService;
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
public class DRoleServiceImpl extends ServiceImpl<DRoleMapper, DRole> implements IDRoleService {

}
