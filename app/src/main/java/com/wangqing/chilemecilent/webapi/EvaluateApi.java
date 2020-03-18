package com.wangqing.chilemecilent.webapi;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.EvaluateBrowserDto;
import com.wangqing.chilemecilent.object.dto.EvaluateDetailDto;
import com.wangqing.chilemecilent.object.dto.EvaluatePostDto;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface EvaluateApi {
    /**
     * 增加一条校园餐饮评价
     * @param evaluate
     * @param accessToken
     * @return postId包装公共对象
     */
    @POST("api/v1/evaluate/post")
    Call<CommonResult<Integer>> addEvaluatePost(@Body EvaluatePostDto evaluate, @Query("access_token") String accessToken);

    @GET("api/v1/evaluate/browser")
    Call<CommonResult<List<EvaluateBrowserDto>>> getEvaluateBrowser(@Query("userId") Integer userId, @Query("hallId") Integer hallId, @Query("access_token") String accessToken);

    @GET("api/v1/evaluate/detail")
    Call<CommonResult<EvaluateDetailDto>> getEvaluateDetail(@QueryMap Map<String, Integer> map, @Query("access_token") String accessToken);
}
