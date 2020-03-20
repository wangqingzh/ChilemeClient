package com.wangqing.chilemecilent.object.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * message reply
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageReplyDto {
    /* 评论类型 评论 回复 */
    private Integer commentType;
    /* 评论ID */
    private Integer commentId;
    /* 帖子类型 校园餐饮评价 美食推荐 */
    private Integer postType;
    /* 帖子ID */
    private Integer postId;
    /* 发帖人ID */
    private Integer postUserId;
    /* 回复内容 */
    private String replyContent;
    /* 原始内容 */
    private String originalContent;
    /* 评论时间 */
    private Date time;
    /* 用户昵称 */
    private String userName;
    /* 用户头像 */
    private String userAvatar;
}
