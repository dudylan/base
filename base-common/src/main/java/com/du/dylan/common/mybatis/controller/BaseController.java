package com.du.dylan.common.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BaseController<T> {

    @Resource
    private IService<T> service;

    @PostMapping
    public R save(T vo){
        service.save(vo);
        return R.ok(null);
    }

    /*本身准备写分页列表 可是发现条件不一样暂不开发*/
    /*
        @GetMapping
        public Page<T> list(Page p,T vo){

            return service.page(p,new QueryWrapper<>());
        }
    */

    @GetMapping("/{id}")
    public R<T> detail(@PathVariable("id") Long id){
        return R.ok(service.getById(id));
    }


    @PutMapping("/{id}")
    public R u(@PathVariable("id") Long id,T vo){
        service.update(vo,new UpdateWrapper<T>().eq("id",id));
        return R.ok(null);
    }


    @DeleteMapping("/{id}")
    public R d(@PathVariable("id") Long id){
        service.removeById(id);
        return R.ok(null);
    }
}
