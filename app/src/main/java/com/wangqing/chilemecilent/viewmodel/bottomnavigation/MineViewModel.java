package com.wangqing.chilemecilent.viewmodel.bottomnavigation;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.UserInfoDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.MineApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 我的 页面 数据处理
 */
public class MineViewModel extends AndroidViewModel {
    private MutableLiveData<UserInfoDto> info;
    private AccountManager accountManager;


    public MineViewModel(@NonNull Application application) {
        super(application);
        this.accountManager = AccountManager.getInstance(application);
    }


    public MutableLiveData<UserInfoDto> getInfo() {
        if (info == null){
            info = new MutableLiveData<>();
            getUserInfoFromServer();
        }
        return info;
    }

    /**
     * 从服务端获取用户信息数据
     */
    public void getUserInfoFromServer(){
        if (!accountManager.isOnline()){
            return;
        }
        MineApi mineApi = RetrofitHandle.getInstance().getRetrofit().create(MineApi.class);
        Call<CommonResult<UserInfoDto>> task = mineApi.getUserInfoByUserId(accountManager.getUser().getUserId(), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<UserInfoDto>>() {
            @Override
            public void onResponse(Call<CommonResult<UserInfoDto>> call, Response<CommonResult<UserInfoDto>> response) {
                info.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<CommonResult<UserInfoDto>> call, Throwable t) {

            }
        });
    }


}
