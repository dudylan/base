package com.du.dylan.shiroweb.controller;


import com.du.dylan.basedao.entity.DUser;
import com.du.dylan.basedao.service.IDUserService;
import com.du.dylan.common.mybatis.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dylan
 * @since 2021-03-25
 */
@RestController
@RequestMapping("/d-user")
public class DUserController extends BaseController<IDUserService, DUser> {

}
