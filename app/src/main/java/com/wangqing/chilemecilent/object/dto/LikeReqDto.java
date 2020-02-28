package com.wangqing.chilemecilent.object.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class LikeReqDto {
    /* 点赞的id */
    @NonNull
    private Integer typeId;
    /* 0 帖子 1 评论  */
    @NonNull
    private Integer type;
    /* 点赞的用户id */
    private Integer userId;
}