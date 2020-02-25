package com.wangqing.chilemecilent.object.dto;

import lombok.Data;

/**
 * 美食推荐贴
 */
@Data
public class FoodRecPostDto {
    private String headline;
    /* 帖子内容 */
    private String content;
    /* 帖子分区 */
    private Integer classifyId;
    /* 综合推荐 */
    private float recommendScore;
    /* 发帖人 */
    private Integer postUserId;
}