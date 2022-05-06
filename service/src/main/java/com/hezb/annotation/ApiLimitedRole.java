package com.hezb.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Component
public @interface ApiLimitedRole {
    String[] roleCodeList() default {};
}
