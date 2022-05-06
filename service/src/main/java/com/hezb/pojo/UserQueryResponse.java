package com.hezb.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryResponse {

    /**
     * 用户编号
     */
    private Long userId;

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
     * 是否已关注
     */
    private Boolean followed;
}
