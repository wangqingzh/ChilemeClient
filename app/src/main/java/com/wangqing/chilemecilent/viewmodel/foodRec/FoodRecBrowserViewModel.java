package com.wangqing.chilemecilent.viewmodel.foodRec;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.FoodRecBroReqDto;
import com.wangqing.chilemecilent.object.dto.FoodRecBrowserDto;
import com.wangqing.chilemecilent.object.dto.LikeReqDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.FoodRecApi;
import com.wangqing.chilemecilent.webapi.LikeFavoriteAttentionApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRecBrowserViewModel extends AndroidViewModel {
    private MutableLiveData<List<FoodRecBrowserDto>> foodRecList;
    private AccountManager accountManager;
    private int classifyId;

    public FoodRecBrowserViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public MutableLiveData<List<FoodRecBrowserDto>> getFoodRecList() {
        if (foodRecList == null){
            foodRecList = new MutableLiveData<>();
            getFoodRecListFromServer();
        }
        return foodRecList;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public void getFoodRecListFromServer(){
        FoodRecApi foodRecApi = RetrofitHandle.getInstance().getRetrofit().create(FoodRecApi.class);
        FoodRecBroReqDto foodRecBroReqDto = new FoodRecBroReqDto(accountManager.getUser().getUserId(), classifyId);
        Call<CommonResult<List<FoodRecBrowserDto>>> task = foodRecApi.getFoodRecList(foodRecBroReqDto, accountManager.getToken());

        task.enqueue(new Callback<CommonResult<List<FoodRecBrowserDto>>>() {
            @Override
            public void onResponse(Call<CommonResult<List<FoodRecBrowserDto>>> call, Response<CommonResult<List<FoodRecBrowserDto>>> response) {
                if (response.code() == 200){
                    if (response.body().getData() == null){
                        foodRecList.setValue(new ArrayList<>());
                    }else {
                        foodRecList.setValue(response.body().getData());
                    }

                }else {
                    Toast.makeText(getApplication(), "请检查网络", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommonResult<List<FoodRecBrowserDto>>> call, Throwable t) {

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
