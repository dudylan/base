package com.du.dylan.common.mybatis.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.du.dylan.common.mybatis.entity.BaseEntity;
import com.du.dylan.common.respone.Rb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


public abstract class BaseController<M extends IService<T>,T extends BaseEntity> {

    @Autowired
    protected  M service;

    @PostMapping
    public Rb save(@RequestBody  T vo){
        service.save(vo);
        return Rb.ok(null);
    }

    /*本身准备写分页列表 可是发现条件不一样暂不开发*/
    /*
        @GetMapping
        public Page<T> list(Page p,T vo){

            return service.page(p,new QueryWrapper<>());
        }
    */

    @GetMapping("/{id}")
    public Rb<T> detail(@PathVariable("id") Long id){
        return Rb.ok(service.getById(id));
    }


    @PutMapping("/{id}")
    public Rb u(@PathVariable("id") Long id, @RequestBody T vo){
        vo.setId(id);
        service.updateById(vo);
        return Rb.ok(null);
    }


    @DeleteMapping("/{id}")
    public Rb d(@PathVariable("id") Long id){
        service.removeById(id);
        return Rb.ok(null);
    }
}
