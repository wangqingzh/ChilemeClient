package com.wangqing.chilemecilent.object.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 根据帖子id　和　用户id　访问评论信息时返回的封装信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentBrowserDto {
    private Integer postId;
    private String comment;
    private Integer fromUid;
    private Integer toUid;
    private Date writeTime;

    private String userName;
    private String userAvatar;

    private Integer likeNumber;
    /* 当前用户是否点过赞 */
    private boolean likeStatus;
}