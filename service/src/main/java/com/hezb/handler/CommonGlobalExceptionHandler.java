package com.hezb.handler;

import com.hezb.exception.CustomizeException;
import com.hezb.exception.enums.BaseExceptionEnum;
import com.hezb.pojo.CommonResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CommonGlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public CommonResponse<String> BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return CommonResponse.fail(BaseExceptionEnum.FAIL.getErrorCode(), message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public CommonResponse<String> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return CommonResponse.fail(BaseExceptionEnum.FAIL.getErrorCode(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResponse<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return CommonResponse.fail(BaseExceptionEnum.FAIL.getErrorCode(), message);
    }

    @ExceptionHandler(value = CustomizeException.class)
    @ResponseBody
    public CommonResponse<String> commonExceptionHandler(CustomizeException e) {
        return CommonResponse.fail(e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse<String> exceptionHandler(Exception e) {
        e.printStackTrace();
        return CommonResponse.fail(BaseExceptionEnum.FAIL.getErrorCode(), e.getMessage());
    }
}
