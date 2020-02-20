package com.wangqing.chilemecilent.webapi;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.PostDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PostApi {
    /* 发布帖子 返回帖子的id */
    @POST("api/v1/post")
    Call<CommonResult<Integer>> addPost(@Body PostDto postDto, @Query("access_token") String accessToken);
}
