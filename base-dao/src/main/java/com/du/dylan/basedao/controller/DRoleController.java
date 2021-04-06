package com.du.dylan.basedao.controller;


import com.du.dylan.basedao.entity.DRole;
import com.du.dylan.basedao.service.IDRoleService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.du.dylan.common.mybatis.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dylan
 * @since 2021-03-25
 */
@RestController
@RequestMapping("/d-role")
public class DRoleController extends BaseController<IDRoleService, DRole> {

}
