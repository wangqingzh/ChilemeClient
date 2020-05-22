package com.wangqing.chilemecilent.viewmodel.mine;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.FoodGalleryDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.MineApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodGalleryViewModel extends AndroidViewModel {

    private MutableLiveData<List<FoodGalleryDto>> galleryList;

    private AccountManager accountManager;

    private MineApi mineApi;

    public FoodGalleryViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
        mineApi = RetrofitHandle.getInstance().getRetrofit().create(MineApi.class);
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public MutableLiveData<List<FoodGalleryDto>> getGalleryList() {
        if (galleryList == null){
            galleryList = new MutableLiveData<>();
            getGalleryListFromServer();
        }
        return galleryList;
    }

    public void getGalleryListFromServer(){
        Call<CommonResult<List<FoodGalleryDto>>> task = mineApi.getFoodGallery(accountManager.getUser().getUserId(), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<List<FoodGalleryDto>>>() {
            @Override
            public void onResponse(Call<CommonResult<List<FoodGalleryDto>>> call, Response<CommonResult<List<FoodGalleryDto>>> response) {
                if (response.code() == 200){
                    getGalleryList().setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CommonResult<List<FoodGalleryDto>>> call, Throwable t) {

            }
        });
    }
}
