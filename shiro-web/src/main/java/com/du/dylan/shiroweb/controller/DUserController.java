package com.du.dylan.shiroweb.controller;


import com.du.dylan.basedao.entity.DUser;
import com.du.dylan.basedao.service.IDUserService;
import com.du.dylan.basedynamic.config.DynamicDataSourceContextHolder;
import com.du.dylan.common.mybatis.controller.BaseController;
import com.du.dylan.common.respone.Rb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @GetMapping("/a/{id}")
    public Rb adetail(@PathVariable("id") Long id){
        service.getID();
        return Rb.ok(null);
    }

    @GetMapping("/db/{name}")
    public Rb dbname(@PathVariable("name") String name){
        DynamicDataSourceContextHolder.setDataSourceKey(name);
        return Rb.ok(service.getById(1));
    }
}
