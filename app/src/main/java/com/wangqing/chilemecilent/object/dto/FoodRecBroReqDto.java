package com.wangqing.chilemecilent.object.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取美食推荐 请求体
 */
@Data
@AllArgsConstructor
public class FoodRecBroReqDto {
    Integer userId;
    Integer classifyId;
}