package com.wangqing.chilemecilent.webapi;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.FoodGalleryDto;
import com.wangqing.chilemecilent.object.dto.HotListDto;
import com.wangqing.chilemecilent.object.dto.UserInfoDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MineApi {
    /* 根据用户id获取 用户信息 需登录后使用access_token */
    @GET("api/v1/mine/{userId}")
    Call<CommonResult<UserInfoDto>> getUserInfoByUserId(@Path("userId") Integer userId, @Query("access_token") String access_token);

    @GET("api/v1/mine/gallery/{userId}")
    Call<CommonResult<List<FoodGalleryDto>>> getFoodGallery(@Path("userId") Integer userId, @Query("access_token") String access_token);


    @GET("api/v1/mine/hotList")
    Call<CommonResult<List<HotListDto>>> getHotList( @Query("access_token") String access_token);

}
