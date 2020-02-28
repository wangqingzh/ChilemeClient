package com.wangqing.chilemecilent.webapi;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.LikeReqDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 点赞接口
 */
public interface LikeFavoriteAttentionApi {
    /* 点赞、取消赞 */
    @POST("api/v1/like")
    Call<CommonResult<Object>> giveALike(@Body LikeReqDto likeReqDto, @Query("access_token") String accessToken);

    /* 收藏、取消收藏 */
    @POST("api/v1/favorite")
    Call<CommonResult<Object>> giveAFavorite(@Query("postId") Integer postId, @Query("userId") Integer userId, @Query("access_token") String accessToken);

    /* 关注、取消关注 */
    @POST("api/v1/attention_fans")
    Call<CommonResult<Object>> attentionHandler(@Query("attentionId") Integer attentionId, @Query("fansId") Integer fansId, @Query("access_token") String accessToken);


}
