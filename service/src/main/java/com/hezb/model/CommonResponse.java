package com.hezb.model;

import com.hezb.exception.enums.BaseExceptionEnum;
import lombok.Data;

@Data
public class CommonResponse<T> {

    private String code;

    private String msg;

    private T data;

    private CommonResponse(T data) {
        this.code = BaseExceptionEnum.SUCCESS.getErrorCode();
        this.msg= BaseExceptionEnum.SUCCESS.getErrorMessage();
        this.data = data;
    }

    private CommonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(data);
    }

    public static <T> CommonResponse<T> fail(String code, String msg) {
        return new CommonResponse<>(code, msg);
    }
}
