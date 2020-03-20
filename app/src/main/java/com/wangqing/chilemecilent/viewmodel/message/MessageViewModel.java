package com.wangqing.chilemecilent.viewmodel.message;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.MessageLikeDto;
import com.wangqing.chilemecilent.object.dto.MessageReplyDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.MessageApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageViewModel extends AndroidViewModel {

    private MutableLiveData<List<MessageReplyDto>> replyList;

    private MutableLiveData<List<MessageLikeDto>> likeList;

    private MessageApi messageApi;

    private AccountManager accountManager;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
        messageApi = RetrofitHandle.getInstance().getRetrofit().create(MessageApi.class);
    }

    /* setter & getter */
    public AccountManager getAccountManager() {
        return accountManager;
    }

    public MutableLiveData<List<MessageLikeDto>> getLikeList() {
        if (likeList == null){
            likeList = new MutableLiveData<>();
            getLikeListFromServer();
        }
        return likeList;
    }

    public MutableLiveData<List<MessageReplyDto>> getReplyList() {
        if (replyList == null){
            replyList = new MutableLiveData<>();
            getReplyListFromServer();
        }
        return replyList;
    }



    /* web api */
    public void getLikeListFromServer(){
        Call<CommonResult<List<MessageLikeDto>>> task = messageApi.getMessageLike(accountManager.getUser().getUserId(), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<List<MessageLikeDto>>>() {
            @Override
            public void onResponse(Call<CommonResult<List<MessageLikeDto>>> call, Response<CommonResult<List<MessageLikeDto>>> response) {
                if (response.code() == 200){
                    likeList.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CommonResult<List<MessageLikeDto>>> call, Throwable t) {

            }
        });
    }

    public void getReplyListFromServer(){
        Call<CommonResult<List<MessageReplyDto>>> task = messageApi.getMessageReply(accountManager.getUser().getUserId(), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<List<MessageReplyDto>>>() {
            @Override
            public void onResponse(Call<CommonResult<List<MessageReplyDto>>> call, Response<CommonResult<List<MessageReplyDto>>> response) {
                if (response.code() == 200){
                    replyList.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CommonResult<List<MessageReplyDto>>> call, Throwable t) {

            }
        });

    }




}
