package com.wangqing.chilemecilent.object.ao;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 存放用户认证与基本信息 利用AccountManager管理
 * 持久化到存储 登录时初始化 打开app时进行更新
 */

@Data
public class User implements Serializable {
    /* 用户id */
    private Integer userId;
    /* access_token */
    private String access_token;
    /* refresh_token */
    private String refresh_token;
    /* 过期时间 */
    private LocalDateTime expires_in;
    /* 用户信息 */
    private Info info;

    @Data
    public static class Info implements Serializable{
        /* 用户昵称 */
        private String nickName = "nikeName";
        /* 用户简介 */
        private String intro = null;
        /* 用户头像地址 */
        private String avatarUrl = null;
        /* 用户空间封面 */
        private String coverUrl = null;
        /* 关注数 */
        private Integer attentionNumber;
        /* 粉丝数 */
        private Integer fansNumber;
        /* 帖子数 */
        private Integer postNumber;
        /* 历史数 */
        private Integer historyNumber;
        /* 收藏数 */
        private Integer myFavoriteNumber;
        /* 点赞数 */
        private Integer myLikeNumber;

    }
}
