package com.hezb.aspect;

import com.hezb.constant.RoleCodeConstant;
import com.hezb.constant.UserMomentConstant;
import com.hezb.exception.CustomizeException;
import com.hezb.exception.enums.BaseExceptionEnum;
import com.hezb.mapper.UserRoleMapper;
import com.hezb.pojo.UserMomentReleaseRequest;
import com.hezb.pojo.UserRoleInfo;
import com.hezb.support.UserSupport;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Aspect
@Component
public class ApiLimitedDataAspect {

    private final UserSupport userSupport;

    private final UserRoleMapper userRoleMapper;

    public ApiLimitedDataAspect(UserSupport userSupport, UserRoleMapper userRoleMapper) {
        this.userSupport = userSupport;
        this.userRoleMapper = userRoleMapper;
    }

    @Pointcut("@annotation(com.hezb.annotation.ApiLimitedData)")
    public void authChek() {
    }

    @Before("authChek()")
    public void before(JoinPoint joinPoint) {
        List<String> roleCodeList = userRoleMapper.getUserRoleInfoByUserId(userSupport.getCurrentUserId()).stream().map(UserRoleInfo::getRoleCode).collect(Collectors.toList());
        Arrays.stream(joinPoint.getArgs()).forEach(arg -> {
            /*动态发布参数校验*/
            if (arg instanceof UserMomentReleaseRequest) {
                String momentType = ((UserMomentReleaseRequest) arg).getType();
                if (roleCodeList.contains(RoleCodeConstant.Lv0) && !StringUtils.equals(UserMomentConstant.VIDEO_MOMENT_TYPE, momentType)){
                    throw new CustomizeException(BaseExceptionEnum.FAIL.getErrorCode(), "非法动态类型");
                }
            }
        });
    }
}
