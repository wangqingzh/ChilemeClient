package com.wangqing.chilemecilent.object.dto;

import lombok.Data;

@Data
public class HotListDto {
    /* 帖子id */
    private Integer postId;

    /* 帖子类型 0 校园餐饮 1 美食推荐 */
    private Integer postType;

    /* 帖子标题 */
    private String headline;

    /* 发帖人 */
    private Integer userId;
}
