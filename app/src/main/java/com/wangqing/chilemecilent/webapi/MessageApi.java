package com.wangqing.chilemecilent.webapi;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.MessageLikeDto;
import com.wangqing.chilemecilent.object.dto.MessageReplyDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MessageApi {
    @GET("api/v1/message/reply/{userId}")
    Call<CommonResult<List<MessageReplyDto>>> getMessageReply(@Path("userId") Integer userId, @Query("access_token") String accessToken);


    @GET("api/v1/message/like/{userId}")
    Call<CommonResult<List<MessageLikeDto>>> getMessageLike(@Path("userId") Integer userId, @Query("access_token") String accessToken);
}
