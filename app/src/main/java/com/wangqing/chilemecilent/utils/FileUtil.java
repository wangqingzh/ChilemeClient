package com.wangqing.chilemecilent.utils;

import android.util.Log;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.UploadFileDto;
import com.wangqing.chilemecilent.webapi.FileApi;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileUtil {


    public static void uploadImage(UploadFileDto uploadFileDto, File file){
        FileApi fileApi = RetrofitHandle.getInstance().getRetrofit().create(FileApi.class);



        Map<String, Object> params = new HashMap<>();
        params.put(AppConfig.UP_INFO, uploadFileDto);

        MediaType mediaType = MediaType.parse("/image/jpg");
        RequestBody fileBody = RequestBody.create(mediaType, file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileBody);

        Call<CommonResult<Object>> task = fileApi.uploadFile(params, part);
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {
                if (response.code() == 200){
                    Log.d(AppConfig.TEST_TAG, "onResponse: " + "上传成功");
                }
            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });

    }
}
