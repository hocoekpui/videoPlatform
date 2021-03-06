package com.hezb.exception.enums;

import com.hezb.exception.BaseException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FollowExceptionEnum implements BaseException {

    FOLLOW_YOURSELF("30000", "不能够关注自己哦"),
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
