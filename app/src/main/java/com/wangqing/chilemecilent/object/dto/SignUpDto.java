package com.wangqing.chilemecilent.object.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 网络 传递注册信息 对象
 */
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class SignUpDto {
    /* 登录类型 默认phone*/
    private String identityType = "phone";
    /* username */
    @NonNull
    private String identifier;
    /* password */
    @NonNull
    private String credential;
    /* 密保问题 */
    @NonNull
    private String securityQuestion;
    /* 密保答案 */
    @NonNull
    private String securityAnswer;
}
