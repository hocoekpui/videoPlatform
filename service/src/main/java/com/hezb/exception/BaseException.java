package com.hezb.exception;

public interface BaseException {

    String getErrorCode();

    String getErrorMessage();

    default void assertTrue(boolean condition, String... params) {
        if (!condition) {
            String errorMessage = this.getErrorMessage();
            if (params != null && params.length > 0) {
                errorMessage = String.format(errorMessage, params);
            }
            throw new CustomizeException(this.getErrorCode(), errorMessage);
        }
    }
}
