package com.du.dylan.common.constants;


import com.du.dylan.common.mybatisplus.api.IErrorCode;

public enum ErrorEnum implements IErrorCode {
    /*
     * 错误信息
     * */
    E_400(400, "请求处理异常，请稍后再试"),
    E_404(404, "请求路径找不到"),
    E_500(500, "服务器异常，请联系管理员"),
    E_501(501, "请求路径不存在"),
    E_502(502, "权限不足"),
    E_10008(10008, "角色删除失败,尚有用户属于此角色"),
    E_10007(10007, "手机号不存在"),
    E_10009(10009, "账户已存在"),

    E_20011(20011, "登陆已过期,请重新登陆"),

    E_90003(90003, "缺少必填参数");

    private long errorCode;

    private String errorMsg;

    ErrorEnum(long errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public long getCode() {
        return this.errorCode;
    }

    @Override
    public String getMsg() {
        return errorMsg;
    }
}
