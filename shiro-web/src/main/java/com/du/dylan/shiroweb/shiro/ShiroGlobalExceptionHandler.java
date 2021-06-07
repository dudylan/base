package com.du.dylan.shiroweb.shiro;

import com.du.dylan.common.constants.ErrorEnum;
import com.du.dylan.common.respone.Rb;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 2)
@Slf4j
public class ShiroGlobalExceptionHandler {


    /*
     *  权限不够
     * */
    @ExceptionHandler({UnauthenticatedException.class})
    public Rb doError(UnauthenticatedException e){
        log.error("UnauthenticatedException:{}",e);
        return Rb.failed(ErrorEnum.E_502);
    }
    /*
     *  权限不够
     * */
    @ExceptionHandler({UnauthorizedException.class})
    public Rb doError(UnauthorizedException e){
        log.error("UnauthorizedException:{}",e);
        return Rb.failed(ErrorEnum.E_502);
    }
}
