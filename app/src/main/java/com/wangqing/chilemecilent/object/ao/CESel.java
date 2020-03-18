package com.wangqing.chilemecilent.object.ao;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 校园餐饮评价　详情　查询
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CESel implements Serializable {
    private Integer postId;
    private Integer postUserId;
    private Integer userId;
}