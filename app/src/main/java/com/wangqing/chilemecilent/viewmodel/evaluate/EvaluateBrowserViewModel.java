package com.wangqing.chilemecilent.viewmodel.evaluate;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.EvaluateBrowserDto;
import com.wangqing.chilemecilent.object.dto.LikeReqDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.EvaluateApi;
import com.wangqing.chilemecilent.webapi.LikeFavoriteAttentionApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EvaluateBrowserViewModel extends AndroidViewModel {

    private MutableLiveData<List<EvaluateBrowserDto>> evaluateList;

    private Integer hallId;
    private AccountManager accountManager;


    /**
     * 构造方法
     * @param application
     */
    public EvaluateBrowserViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
    }



    /* setter & getter */
    public MutableLiveData<List<EvaluateBrowserDto>> getEvaluateList() {
        if (evaluateList == null){
            evaluateList = new MutableLiveData<>();
            getEvaluateBrowserFromServer();
        }
        return evaluateList;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }


    /* web api */

    public void getEvaluateBrowserFromServer(){
        EvaluateApi evaluateApi = RetrofitHandle.getInstance().getRetrofit().create(EvaluateApi.class);
        Call<CommonResult<List<EvaluateBrowserDto>>> task = evaluateApi.getEvaluateBrowser(accountManager.getUser().getUserId(), hallId, accountManager.getToken());
        task.enqueue(new Callback<CommonResult<List<EvaluateBrowserDto>>>() {
            @Override
            public void onResponse(Call<CommonResult<List<EvaluateBrowserDto>>> call, Response<CommonResult<List<EvaluateBrowserDto>>> response) {
                if (response.code() == 200){
                    getEvaluateList().setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CommonResult<List<EvaluateBrowserDto>>> call, Throwable t) {

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
