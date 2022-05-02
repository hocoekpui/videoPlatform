package com.hezb.exception.enums;

import com.hezb.exception.BaseException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserExceptionEnum implements BaseException {

    PHONE_EXIST("20000", "手机号码已经被注册"),
    PHONE_NOT_EXIST("20001", "注册手机号码不存在"),
    USER_NOT_EXIST("20002", "用户信息不存在"),
    PASSWORD_NOT_CORRECT("20003", "用户密码不正确"),
    USER_TOKEN_EXCEED("20004", "用户身份认证信息过期"),
    ;

    private final String errorCode;
    private final String errorMessage;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
