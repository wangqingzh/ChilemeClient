package com.wangqing.chilemecilent.object.ao;

import com.wangqing.chilemecilent.object.dto.UserInfoDto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 存放用户认证与基本信息 利用AccountManager管理
 * 持久化到存储 登录时初始化 打开app时进行更新
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
    private LocalDateTime expires_in;
    /* 用户信息 */
    private UserInfoDto info;

}
