package com.wangqing.chilemecilent.viewmodel.dynamic;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.DynamicDto;
import com.wangqing.chilemecilent.object.dto.LikeReqDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.DynamicApi;
import com.wangqing.chilemecilent.webapi.LikeFavoriteAttentionApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DynamicViewModel extends AndroidViewModel {

    private MutableLiveData<List<DynamicDto>> dynamicList;

    private AccountManager accountManager;

    /**
     * 构造方法
     * @param application
     */
    public DynamicViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
    }

    /* setter & getter */
    public AccountManager getAccountManager() {
        return accountManager;
    }

    public MutableLiveData<List<DynamicDto>> getDynamicList() {
        if (dynamicList == null){
            dynamicList = new MutableLiveData<>();
            getDynamicListFromServer();
        }
        return dynamicList;
    }

    /* web api  */

    public void getDynamicListFromServer() {
        DynamicApi dynamicApi = RetrofitHandle.getInstance().getRetrofit().create(DynamicApi.class);
        Call<CommonResult<List<DynamicDto>>> task = dynamicApi.getDynamicList(accountManager.getUser().getUserId(), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<List<DynamicDto>>>() {
            @Override
            public void onResponse(Call<CommonResult<List<DynamicDto>>> call, Response<CommonResult<List<DynamicDto>>> response) {
                if (response.code() == 200){
                    getDynamicList().setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CommonResult<List<DynamicDto>>> call, Throwable t) {

            }
        });
    }

    public void giveALike(LikeReqDto likeReqDto){
        likeReqDto.setUserId(accountManager.getUser().getUserId());
        LikeFavoriteAttentionApi likeFavoriteAttentionApi = RetrofitHandle.getInstance().getRetrofit().create(LikeFavoriteAttentionApi.class);
        Call<CommonResult<Object>> task = likeFavoriteAttentionApi.giveALike(likeReqDto, accountManager.getToken());
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {

            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });
    }

}
