package com.hezb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FanUserInfoResponse {

    /**
     * 注册手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 签名
     */
    private String sign;

    /**
     * 性别，0：男，1：女，2：未知
     */
    private String gender;

    /**
     * 生日
     */
    private String birth;

    /**
     * 是否互相关注
     */
    private Boolean followEachOther;
}
