package com.du.dylan.basedao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.du.dylan.basedao.entity.DMenu;
import com.du.dylan.basedao.mapper.DMenuMapper;
import com.du.dylan.basedao.service.IDMenuService;
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
public class DMenuServiceImpl extends ServiceImpl<DMenuMapper, DMenu> implements IDMenuService {

}
