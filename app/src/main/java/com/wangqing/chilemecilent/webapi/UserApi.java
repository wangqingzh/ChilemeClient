package com.wangqing.chilemecilent.webapi;





import com.wangqing.chilemecilent.object.ao.CheckToken;
import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.ao.Token;
import com.wangqing.chilemecilent.object.dto.SignUpDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.DELETE;

public interface UserApi {
    /* 注册 */
    @POST("api/v1/user")
    Call<CommonResult<Object>> signUp(@Body SignUpDto signUpDto);

    /* 获取token */
    @POST("oauth/token?grant_type=password&scope=all&client_id=user&client_secret=123")
    Call<Token> getToken(@Query("username") String userName, @Query("password") String passWord);

    /* 刷新token */
    @POST("oauth/token?grant_type=refresh_token&scope=all&client_id=user&client_secret=123")
    Call<Token> refreshToken(@Query("refresh_token") String refreshToken);

    /* 确认token */
    @POST("oauth/check_token")
    Call<CheckToken> checkToken(@Query("token") String token);

    /* 清除token 退出登录 */
    @DELETE("oauth/token")
    Call<CommonResult<Object>> revokeToken(@Query("access_token") String accessToken);
}
