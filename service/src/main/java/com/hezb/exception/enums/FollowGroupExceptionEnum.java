package com.hezb.exception.enums;

import com.hezb.exception.BaseException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FollowGroupExceptionEnum implements BaseException {

    FOLLOW_GROUP_EXIST("40000", "分组已存在"),
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
