package com.wangqing.chilemecilent.object.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostBrowserDto {
    /* 帖子号 */
    private Integer postId;
    /* 发帖人id */
    private Integer postUserId;
    /* 发帖人姓名 */
    private String postUserName;
    /* 发帖人头像 */
    private String postUserAvatar;
    /* 发帖时间 */
    private Date postTime;
    /* 帖子标题 */
    private String headline;
    /* 帖子图片 */
    private String postImage;
    /* 点赞数 */
    private Integer likeNumber;
    /* 收藏数 */
    private Integer favoriteNumber;
    /* 评论数 */
    private Integer commentNumber;
}
