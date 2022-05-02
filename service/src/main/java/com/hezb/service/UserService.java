package com.hezb.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hezb.constant.UserConstant;
import com.hezb.domain.User;
import com.hezb.domain.UserInfo;
import com.hezb.exception.enums.UserExceptionEnum;
import com.hezb.mapper.UserInfoMapper;
import com.hezb.mapper.UserMapper;
import com.hezb.model.QueryUserResponse;
import com.hezb.model.RegisterUserRequest;
import com.hezb.model.UserInfoUpdateRequest;
import com.hezb.model.UserLoginRequest;
import com.hezb.util.MD5Util;
import com.hezb.util.RSAUtil;
import com.hezb.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;

    public UserService(UserMapper userMapper, UserInfoMapper userInfoMapper) {
        this.userMapper = userMapper;
        this.userInfoMapper = userInfoMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long register(RegisterUserRequest request) throws Exception {
        /*判断注册手机号是否已被注册*/
        Long userAmount = userMapper.selectCount(Wrappers.<User>lambdaQuery().eq(User::getPhone, request.getPhone()));
        UserExceptionEnum.PHONE_EXIST.assertTrue(userAmount == 0);
        /*密码解密*/
        String password = request.getPassword();
        String rawPassword = RSAUtil.decrypt(password);
        /*根据盐值对密码加密*/
        Date now = new Date();
        String salt = String.valueOf(now);
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        /*新增用户*/
        User user = User.builder().phone(request.getPhone()).email(request.getEmail()).salt(salt).password(md5Password).createTime(now).build();
        userMapper.insert(user);
        /*新增用户信息*/
        UserInfo userInfo = UserInfo.builder().userId(user.getId()).nickName(UserConstant.DEFAULT_NICK_NAME).birth(UserConstant.DEFAULT_BIRTHDAY).gender(UserConstant.GENDER_UNKNOWN).createTime(now).build();
        userInfoMapper.insert(userInfo);
        return user.getId();
    }

    public String login(UserLoginRequest request) throws Exception {
        /*判断注册手机号是否存在*/
        List<User> userList = userMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getPhone, request.getPhone()));
        UserExceptionEnum.PHONE_NOT_EXIST.assertTrue(CollectionUtils.isNotEmpty(userList));
        /*判断登陆用户是否存在*/
        User user = userList.stream().findFirst().orElse(null);
        UserExceptionEnum.USER_NOT_EXIST.assertTrue(user != null);
        /*判断登陆密码是否正确*/
        String salt = user.getSalt();
        String password = request.getPassword();
        String rawPassword = RSAUtil.decrypt(password);
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        UserExceptionEnum.PASSWORD_NOT_CORRECT.assertTrue(StringUtils.equals(md5Password, user.getPassword()));
        /*返回令牌信息*/
        return TokenUtil.generateToken(user.getId());
    }

    public QueryUserResponse getUserInfo(Long userId) {
        /*根据用户编号查询用户基础信息*/
        User user = userMapper.selectById(userId);
        UserExceptionEnum.USER_NOT_EXIST.assertTrue(user != null);
        QueryUserResponse userResponse = QueryUserResponse.builder().userId(user.getId()).phone(user.getPhone()).email(user.getEmail()).build();
        /*根据用户编号查询用户详情信息*/
        List<UserInfo> userInfoList = userInfoMapper.selectList(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, userId));
        Optional<UserInfo> optionalUserInfo = userInfoList.stream().findFirst();
        if (optionalUserInfo.isPresent()) {
            BeanUtils.copyProperties(optionalUserInfo.get(), userResponse);
        }
        return userResponse;
    }

    public int updateUserInfo(Long userId, UserInfoUpdateRequest request) {
        /*根据用户编号更新用户详情信息*/
        return userInfoMapper.update(null, Wrappers.<UserInfo>lambdaUpdate().eq(UserInfo::getUserId, userId)
                .set(StringUtils.isNotBlank(request.getNickName()), UserInfo::getNickName, request.getNickName())
                .set(StringUtils.isNotBlank(request.getAvatar()), UserInfo::getAvatar, request.getAvatar())
                .set(StringUtils.isNotBlank(request.getSign()), UserInfo::getSign, request.getSign())
                .set(StringUtils.isNotBlank(request.getGender()), UserInfo::getGender, request.getGender())
                .set(StringUtils.isNotBlank(request.getBirth()), UserInfo::getBirth, request.getBirth())
                .set(UserInfo::getUpdateTime, new Date()));
    }
}
