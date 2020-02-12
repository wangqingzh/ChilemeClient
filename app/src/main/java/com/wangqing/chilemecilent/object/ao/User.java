package com.wangqing.chilemecilent.object.ao;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 存放用户认证与基本信息
 */

@Data
public class User implements Serializable {
    /* 用户id */
    private Integer userId;
    /* access_token */
    private String access_token;
    /* refresh_token */
    private String refresh_token;
    /* 过期时间 */
    private Date expires_in;

    private Info info;

    static class Info implements Serializable{

    }
}
