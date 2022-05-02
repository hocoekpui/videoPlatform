package com.hezb.exception.enums;

import com.hezb.exception.BaseException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FollowExceptionEnum implements BaseException {

    NO_FOLLOW_USER("30000", "没有关注"),
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
