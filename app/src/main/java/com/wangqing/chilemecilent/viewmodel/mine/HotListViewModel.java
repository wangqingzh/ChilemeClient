package com.wangqing.chilemecilent.viewmodel.mine;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.HotListDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.MineApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotListViewModel extends AndroidViewModel {
    private MutableLiveData<List<HotListDto>> hotList;

    private AccountManager accountManager;

    private MineApi mineApi;

    public HotListViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
        mineApi = RetrofitHandle.getInstance().getRetrofit().create(MineApi.class);
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public MutableLiveData<List<HotListDto>> getHotList() {
        if (hotList == null){
            hotList = new MutableLiveData<>();
            getHotListFromServer();
        }
        return hotList;
    }

    public void getHotListFromServer(){
        Call<CommonResult<List<HotListDto>>> task = mineApi.getHotList(accountManager.getToken());
        task.enqueue(new Callback<CommonResult<List<HotListDto>>>() {
            @Override
            public void onResponse(Call<CommonResult<List<HotListDto>>> call, Response<CommonResult<List<HotListDto>>> response) {
                if (response.code() == 200){
                    getHotList().setValue(response.body().getData());
                }
            }
            @Override
            public void onFailure(Call<CommonResult<List<HotListDto>>> call, Throwable t) {

            }
        });
    }
}
