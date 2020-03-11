package com.wangqing.chilemecilent.viewmodel.evaluate;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.EvaluatePostDto;
import com.wangqing.chilemecilent.object.dto.UploadFileDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.AppConfig;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.EvaluateApi;
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

public class EvaluatePostViewModel extends AndroidViewModel {

    private MutableLiveData<EvaluatePostDto> evaluate;
    private MutableLiveData<File> image;

    private AccountManager accountManager;

    /**
     * 构造方法
     * @param application
     */
    public EvaluatePostViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
    }

    /* setter & getter */

    public MutableLiveData<EvaluatePostDto> getEvaluate() {
        if (evaluate == null){
            evaluate = new MutableLiveData<>();
            evaluate.setValue(new EvaluatePostDto());
        }
        return evaluate;
    }

    public MutableLiveData<File> getImage() {
        if (image == null){
            image = new MutableLiveData<>();
        }
        return image;
    }



    /* web api */
    public void addPost(){
        EvaluatePostDto evaluate = getEvaluate().getValue();
        evaluate.setPostUserId(accountManager.getUser().getUserId());

        EvaluateApi evaluateApi = RetrofitHandle.getInstance().getRetrofit().create(EvaluateApi.class);
        Call<CommonResult<Integer>> task = evaluateApi.addEvaluatePost(evaluate, accountManager.getToken());
        task.enqueue(new Callback<CommonResult<Integer>>() {
            @Override
            public void onResponse(Call<CommonResult<Integer>> call, Response<CommonResult<Integer>> response) {
                if (response.code() == 200 && getImage().getValue()!=null){
                    uploadImage(response.body().getData());
                    // 上传图片
                }else if (response.code() == 200){
                    Toast.makeText(getApplication(), "发布成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommonResult<Integer>> call, Throwable t) {

            }
        });
    }


    public void uploadImage(Integer postId){
        FileApi fileApi = RetrofitHandle.getInstance().getRetrofit().create(FileApi.class);

        File file = getImage().getValue();

        Map<String, Object> params = new HashMap<>();
        params.put(AppConfig.UP_INFO, new UploadFileDto(UploadFileDto.EVALUATION_IMAGE, postId));

        MediaType mediaType = MediaType.parse("/image/jpg");
        RequestBody fileBody = RequestBody.create(mediaType, file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileBody);

        Call<CommonResult<Object>> task = fileApi.uploadFile(params, part);
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {
                if (response.code() == 200){
                    Log.d(AppConfig.TEST_TAG, "onResponse: " + "上传成功");
                    Toast.makeText(getApplication(), "发布成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });

    }






}
