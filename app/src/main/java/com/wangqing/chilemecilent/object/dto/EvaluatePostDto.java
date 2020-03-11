package com.wangqing.chilemecilent.object.dto;
import lombok.Data;
import lombok.ToString;

/**
 * 校园餐饮评价贴
 */
@Data
@ToString
public class EvaluatePostDto {
    /* 标题 */
    private String headline;
    /* 店名 */
    private String storeName;
    /* 菜品 */
    private String dish;
    /* 点评 */
    private String content;
    /* 食堂 默认0 东苑*/
    private Integer hallId = 0;
    /* 打分 */
    private float evaluateScore;
    /* 发帖人 */
    private Integer postUserId;
}