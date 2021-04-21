package com.du.dylan.common.exception;

import com.du.dylan.common.constants.ErrorEnum;
import com.du.dylan.common.respone.Rb;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Rb emptyResultExceptionHandler(Exception e){
         log.error("未知异常：{}",e);
         return Rb.failed(ErrorEnum.E_500);
    }


    /*
    *  权限不够
    * */
    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public Rb doError(AuthorizationException e){
        log.error("AuthorizationException:{}",e);
        return Rb.failed(ErrorEnum.E_502);
    }

    //请求路径找不到
    @ExceptionHandler(NoHandlerFoundException.class)
    public Rb doError(NoHandlerFoundException e){
        log.error("NoHandlerFoundException:{}",e);
        return Rb.failed(ErrorEnum.E_404);
    }

    //请求参数错误异常
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Rb doError(MissingServletRequestParameterException e){
        log.error("MissingServletRequestParameterException:{}",e);
        return Rb.failed(ErrorEnum.E_400);
    }
}
