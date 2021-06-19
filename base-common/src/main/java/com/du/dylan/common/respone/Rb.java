package com.du.dylan.common.respone;


import com.du.dylan.common.mybatisplus.api.IErrorCode;
import com.du.dylan.common.mybatisplus.enums.ApiErrorCode;
import com.du.dylan.common.mybatisplus.exceptions.ApiException;

import java.io.Serializable;
import java.util.Optional;

public class Rb<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private long code;
    private T data;
    private String msg;

    private Integer count;

    public Rb() {
    }

    public Rb(IErrorCode errorCode) {
        errorCode = (IErrorCode)Optional.ofNullable(errorCode).orElse(ApiErrorCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> Rb<T> ok(T data) {
        ApiErrorCode aec = ApiErrorCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ApiErrorCode.FAILED;
        }

        return restResult(data, aec);
    }

    public static <T> Rb<T> pageOk(T data,Integer count) {
        ApiErrorCode aec = ApiErrorCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ApiErrorCode.FAILED;
        }
        return restResult(data, aec, count);
    }

    public static <T> Rb<T> failed(String msg) {
        return restResult(null, ApiErrorCode.FAILED.getCode(), msg,null);
    }

    public static <T> Rb<T> failed(IErrorCode errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> Rb<T> restResult(T data, IErrorCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg(),null);
    }

    public static <T> Rb<T> restResult(T data, IErrorCode errorCode,Integer count) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg(),count);
    }

    private static <T> Rb<T> restResult(T data, long code, String msg,Integer count) {
        Rb<T> apiResult = new Rb();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        apiResult.setCount(count);
        return apiResult;
    }

    public boolean ok() {
        return ApiErrorCode.SUCCESS.getCode() == this.code;
    }

    public T serviceData() {
        if (!this.ok()) {
            throw new ApiException(this.msg);
        } else {
            return this.data;
        }
    }

    public long getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public Integer getCount() {
        return this.count;
    }


    public Rb<T> setCode(final long code) {
        this.code = code;
        return this;
    }

    public Rb<T> setData(final T data) {
        this.data = data;
        return this;
    }

    public Rb<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    public Rb<T> setCount(final Integer count){
        this.count = count;
        return this;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof com.du.dylan.common.mybatisplus.api.R;
    }

    @Override
    public String toString() {
        return "R(code=" + this.getCode() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ", count=" + this.getCount() + ")";
    }
}
