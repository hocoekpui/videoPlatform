package com.hezb.exception.enums;

import com.hezb.exception.BaseException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BaseExceptionEnum implements BaseException {

    SUCCESS("10000", "成功"),
    FAIL("10001", "失败"),
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
