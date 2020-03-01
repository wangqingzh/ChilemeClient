package com.wangqing.chilemecilent.webapi;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.CommentBrowserDto;
import com.wangqing.chilemecilent.object.dto.CommentPostDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 评论接口
 */
public interface CommentApi {

    /**
     *
     * @param postId 帖子id
     * @param userId 用户id 当前用户的点赞状态
     * @param accessToken 访问token
     * @return
     */
    @GET("api/v1/comment")
    Call<CommonResult<List<CommentBrowserDto>>>
    getComment(@Query("postId") Integer postId, @Query("userId") Integer userId,
               @Query("access_token") String accessToken);


    @POST("api/v1/comment")
    Call<CommonResult<Object>> addComment(@Body CommentPostDto commentPostDto,
                                          @Query("access_token") String accessToken);

}
