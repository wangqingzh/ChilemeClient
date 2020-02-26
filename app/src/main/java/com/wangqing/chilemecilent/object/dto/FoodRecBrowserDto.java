package com.wangqing.chilemecilent.object.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FoodRecBrowserDto {

    /* 帖子号 */
    private Integer postId;
    /* 发帖时间 */
    private Date postTime;
    /* 帖子标题 */
    private String postHeadline;

    /* 帖子图片 */
    private String postImageUrl;


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