package com.du.dylan.shiroweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.du.dylan.basedao.entity.DUser;
import com.du.dylan.basedao.service.IDUserService;
import com.du.dylan.common.constants.ErrorEnum;
import com.du.dylan.common.respone.Rb;
import com.du.dylan.shiroweb.shiro.JwtUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    @Autowired
    private IDUserService userservice;

    @PostMapping("/login")
    public Rb login(@RequestParam("phone") String phone,
                    @RequestParam("password") String password) {
        DUser user = userservice.getOne(new QueryWrapper<DUser>().eq("phone",phone));
        if(user == null){
            return Rb.failed(ErrorEnum.E_10007);
        }else  if (user.getPassword().equals(password)) {
            return Rb.ok(JwtUtil.sign(phone));
        } else {
            throw new UnauthorizedException();
        }
    }

    @RequestMapping("/unauthorized")
    public Rb unauthorized() {
        return Rb.failed(ErrorEnum.E_502);
    }
}
