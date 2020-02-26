package com.wangqing.chilemecilent.viewmodel.foodRec;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.FoodRecPostDto;
import com.wangqing.chilemecilent.object.dto.UploadFileDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.AppConfig;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.FileApi;
import com.wangqing.chilemecilent.webapi.FoodRecApi;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRecPostViewModel extends AndroidViewModel {

    AccountManager accountManager;  // 用户管理

    private MutableLiveData<String> headLine;  // 标题
    private MutableLiveData<String> content; // 内容
    private MutableLiveData<Integer> classifyId; // 分区id
    private MutableLiveData<Float> recommendScore; // 推荐分数
    private MutableLiveData<File> image; // 选择的图片文件路径


    public FoodRecPostViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
    }

    public MutableLiveData<Float> getRecommendScore() {
        if (recommendScore == null){
            recommendScore = new MutableLiveData<>();
        }
        return recommendScore;
    }

    public MutableLiveData<Integer> getClassifyId() {
        if (classifyId == null){
            classifyId = new MutableLiveData<>();
        }
        return classifyId;
    }

    public MutableLiveData<String> getContent() {
        if (content == null){
            content = new MutableLiveData<>();
        }
        return content;
    }

    public MutableLiveData<String> getHeadLine() {
        if (headLine == null){
            headLine = new MutableLiveData<>();
        }
        return headLine;
    }

    public MutableLiveData<File> getImage() {
        if (image == null){
            image = new MutableLiveData<>();
        }
        return image;
    }

    /**
     * 发布帖子 方法
     */
    public void addPost(){
        FoodRecApi foodRecApi = RetrofitHandle.getInstance().getRetrofit().create(FoodRecApi.class);
        FoodRecPostDto foodRecPostDto = new FoodRecPostDto();
        foodRecPostDto.setHeadline(getHeadLine().getValue());
        foodRecPostDto.setContent(getContent().getValue());
        foodRecPostDto.setClassifyId(getClassifyId().getValue());
        foodRecPostDto.setRecommendScore(getRecommendScore().getValue());
        foodRecPostDto.setPostUserId(accountManager.getUser().getUserId());

        Call<CommonResult<Integer>> task = foodRecApi.addPost(foodRecPostDto, accountManager.getToken());
        task.enqueue(new Callback<CommonResult<Integer>>() {
            @Override
            public void onResponse(Call<CommonResult<Integer>> call, Response<CommonResult<Integer>> response) {
                if (response.code() == 200){
                    Log.d(AppConfig.TEST_TAG, "onResponse: " + response.body().getData());
                    uploadImage(response.body().getData());
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
        params.put(AppConfig.UP_INFO, new UploadFileDto(UploadFileDto.POST_IMAGE, postId));

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
