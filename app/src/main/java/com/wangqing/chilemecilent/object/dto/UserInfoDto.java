package com.wangqing.chilemecilent.object.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 从服务端传递过来的用户信息 展示在 “我的” 页面上
 * 并缓存到本地 无网络时使用
 */
@Data
public class UserInfoDto implements Serializable {
    /* 用户id */
    private Integer userId;
//    /* 用户手机号 */
//    private String phoneNumber;
    /* 用户昵称 */
    private String nickName;
    /* 用户简介 */
    private String intro;
    /* 用户头像地址 */
    private String avatarUrl;
    /* 用户空间封面 */
    private String coverUrl;
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
}
