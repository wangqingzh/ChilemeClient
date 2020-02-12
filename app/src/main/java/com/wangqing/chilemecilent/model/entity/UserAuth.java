package com.wangqing.chilemecilent.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 用户认证信息持久化
 */
@Entity(tableName = "user_auth")
@Data
@RequiredArgsConstructor(staticName = "of")
public class UserAuth {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    /* 用户ID */
    @ColumnInfo(name = "user_id")
    @NonNull
    private Integer userId;

    /* accessToken */
    @ColumnInfo(name = "access_token")
    @NonNull
    private String accessToken;

    /* refreshToken */
    @ColumnInfo(name = "refreshToken")
    @NonNull
    private String refreshToken;

    /* 过期时间 */
    @ColumnInfo(name = "expires_in")
    @NonNull
    private Date expiresIn;
}
