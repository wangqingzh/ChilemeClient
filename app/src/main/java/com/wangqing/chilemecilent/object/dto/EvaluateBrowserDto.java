package com.wangqing.chilemecilent.object.dto;



import lombok.Data;

import java.util.Date;

/**
 * 校园餐饮评价　返回
 */
@Data
public class EvaluateBrowserDto {
    /* 帖子号 */
    private Integer postId;
    /* 发帖时间 */
    private Date postTime;
    /* 帖子标题 */
    private String postHeadline;
    /* 帖子图片 */
    private String postImageUrl;

    /* 店名和菜品 */
    private String storeNameDish;

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
    /* 评论数 */
    private Integer commentNumber;
}