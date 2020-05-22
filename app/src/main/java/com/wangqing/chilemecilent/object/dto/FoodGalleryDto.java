package com.wangqing.chilemecilent.object.dto;

import lombok.Data;

@Data
public class FoodGalleryDto {
    private Integer postId;
    /* 帖子类型 0 校园餐饮 1 美食推荐 */
    private Integer postType = 0;

    /* 帖子标题 */
    private String headline;

    /* 发布的图片链接 */
    private String imageUrl = null;

    /* 发帖人 */
    private Integer userId;
}
