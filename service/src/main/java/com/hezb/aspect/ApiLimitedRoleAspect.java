package com.hezb.aspect;

import com.hezb.annotation.ApiLimitedRole;
import com.hezb.exception.CustomizeException;
import com.hezb.exception.enums.BaseExceptionEnum;
import com.hezb.mapper.UserRoleMapper;
import com.hezb.pojo.UserRoleInfo;
import com.hezb.support.UserSupport;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
public class ApiLimitedRoleAspect {

    private final UserSupport userSupport;

    private final UserRoleMapper userRoleMapper;

    public ApiLimitedRoleAspect(UserSupport userSupport, UserRoleMapper userRoleMapper) {
        this.userSupport = userSupport;
        this.userRoleMapper = userRoleMapper;
    }

    @Pointcut("@annotation(com.hezb.annotation.ApiLimitedRole)")
    public void authChek() {
    }

    @Before("authChek() && @annotation(limitedRole)")
    public void before(JoinPoint joinPoint, ApiLimitedRole limitedRole) {
        List<String> roleCodeList = userRoleMapper.getUserRoleInfoByUserId(userSupport.getCurrentUserId()).stream().map(UserRoleInfo::getRoleCode).collect(Collectors.toList());
        Set<String> limitedRoleSet = Arrays.asList(limitedRole.roleCodeList()).stream().collect(Collectors.toSet());
        limitedRoleSet.retainAll(roleCodeList);
        if (limitedRoleSet.size() > 0) {
            throw new CustomizeException(BaseExceptionEnum.FAIL.getErrorCode(), "权限不足");
        }
    }
}
