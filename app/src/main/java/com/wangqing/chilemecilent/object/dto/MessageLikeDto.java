package com.wangqing.chilemecilent.object.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageLikeDto {
    public static final Integer LIKE_POST = 0;
    public static final Integer LIKE_REPLY = 1;
    /* 点赞类型 */
    private Integer likeType;
    /* 被电赞内容　帖子标题或评论回复 */
    private String content;
    /* 点赞时间 */
    private Date likeTime;

    /* 对应帖子类型 */
    private Integer postType;
    /* 对应帖子ＩＤ */
    private Integer postId;
    /* 对应发贴人ＩＤ */
    private Integer postUserId;

    /* 点赞人ＩＤ */
    private Integer likeUserId;
    /* 点赞人昵称 */
    private String likeUserName;
    /* 点赞人头像 */
    private String likeUserAvatar;
}