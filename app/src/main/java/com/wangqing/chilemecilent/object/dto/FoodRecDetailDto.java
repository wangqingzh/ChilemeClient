package com.wangqing.chilemecilent.object.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 获取美食推荐帖子详情
 */
@Data
@AllArgsConstructor
@ToString
public class FoodRecDetailDto {

    /* 帖子号 */
    private Integer postId;
    /* 发帖时间 */
    private Date postTime;
    /* 帖子标题 */
    private String postHeadline;
    /* 帖子图片 */
    private String postImageUrl;


    /* 帖子内容 */
    private String content;
    /* 综合推荐分数 */
    private float recommendScore;


    /* 发帖人id */
    private Integer userId;
    /* 发帖人姓名 */
    private String userName;
    /* 发帖人头像 */
    private String userAvatar;


    /* 点赞数 */
    private Integer likeNumber;
    /* 用户 点赞状态 */
    private boolean likeStatus;

    /* 收藏数 */
    private Integer favoriteNumber;
    /* 收藏状态 */
    private boolean favoriteStatus;

    /* 评论数 */
    private Integer commentNumber;

    /* 关注状态 */
    private boolean attentionStatus;
}