package com.hezb.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomizeException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    private String errorCode;
    private String errorMessage;

    public CustomizeException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
