package com.wangqing.chilemecilent.webapi;

import com.wangqing.chilemecilent.object.ao.CommonResult;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * 上下传文件
 */
public interface FileApi {
    /* 上传文件接口 type = avatar_default/cover/image   id = userId/postId/evaluationId */
    @Multipart
    @POST("/file/upload")
    Call<CommonResult<Object>> uploadFile(@PartMap Map<String, Object> params, @Part MultipartBody.Part file);
}
