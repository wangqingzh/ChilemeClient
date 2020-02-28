package com.wangqing.chilemecilent.webapi;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.FRDSelDto;
import com.wangqing.chilemecilent.object.dto.FoodRecBroReqDto;
import com.wangqing.chilemecilent.object.dto.FoodRecBrowserDto;
import com.wangqing.chilemecilent.object.dto.FoodRecDetailDto;
import com.wangqing.chilemecilent.object.dto.FoodRecPostDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FoodRecApi {
    /* 发布帖子 返回帖子的id */
    @POST("api/v1/food_rec/post")
    Call<CommonResult<Integer>> addPost(@Body FoodRecPostDto foodRecPostDto, @Query("access_token") String accessToken);

    /* 获取美食推荐的帖子 不用登录 */
    @POST("api/v1/food_rec/browser")
    Call<CommonResult<List<FoodRecBrowserDto>>> getFoodRecList(@Body FoodRecBroReqDto foodRecBroReqDto, @Query("access_token") String accessToken);

    /* 根据帖子id 发帖人id  用户id 获取帖子详情 */
    @POST("api/v1/food_rec/detail")
    Call<CommonResult<FoodRecDetailDto>> detailByFRDSel(@Body FRDSelDto frdSelDto, @Query("access_token") String accessToken);
}
