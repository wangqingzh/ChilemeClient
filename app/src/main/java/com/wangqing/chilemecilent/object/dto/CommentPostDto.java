package com.wangqing.chilemecilent.object.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentPostDto {
    /* 主题id */
    private Integer postId;
    /* 评论 */
    private String content;
    /* 评论用户 */
    private Integer fromUid;
    /* 被回复用户 当评论帖子时 to_uid 为空 */
    private Integer toUid;
}