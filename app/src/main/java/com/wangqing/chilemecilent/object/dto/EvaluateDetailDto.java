package com.wangqing.chilemecilent.object.dto;
import lombok.Data;

import java.util.Date;

@Data
public class EvaluateDetailDto {

    /* 帖子号 */
    private Integer postId;
    /* 发帖时间 */
    private Date postTime;
    /* 帖子标题 */
    private String postHeadline;
    /* 帖子图片 */
    private String postImageUrl;

    /* 店名 */
    private String storeName;
    /* 菜品 */
    private String dish;
    /* 点评内容 */
    private String content;
    /* 综合打分 */
    private float evaluateScore;


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