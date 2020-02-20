package com.wangqing.chilemecilent.viewmodel.partition;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.dto.PostDto;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.PostApi;

public class PostViewModel extends AndroidViewModel {
    private MutableLiveData<PostDto> postDto;  // 传递的总信息
    private MutableLiveData<Integer> partitionId; // 分区id
    private MutableLiveData<Float> recommendScore; // 推荐分数


    public PostViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Float> getRecommendScore() {
        if (recommendScore == null){
            recommendScore = new MutableLiveData<>();
        }
        return recommendScore;
    }

    public MutableLiveData<Integer> getPartitionId() {
        if (partitionId == null){
            partitionId = new MutableLiveData<>();
        }
        return partitionId;
    }

    public MutableLiveData<PostDto> getPostDto() {
        if (postDto == null){
            postDto = new MutableLiveData<>();
            postDto.setValue(new PostDto());
        }
        return postDto;
    }


    /**
     * 发布帖子
     */
    public void addPost(){
        PostApi postApi = RetrofitHandle.getInstance().getRetrofit().create(PostApi.class);
    }


}
