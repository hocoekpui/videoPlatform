package com.hezb.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowUserInfoResponse {

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 用户信息汇总
     */
    private List<UserInfoSummary> userInfoSummary;

    @Data
    @Builder
    public static class UserInfoSummary {

        /**
         * 用户编号
         */
        private Long userId;

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
    }
}
