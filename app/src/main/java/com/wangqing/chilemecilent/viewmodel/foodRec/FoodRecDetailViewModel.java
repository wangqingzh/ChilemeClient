package com.wangqing.chilemecilent.viewmodel.foodRec;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.CommentPostDto;
import com.wangqing.chilemecilent.object.dto.FRDSelDto;
import com.wangqing.chilemecilent.object.dto.FoodRecDetailDto;
import com.wangqing.chilemecilent.object.dto.LikeReqDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.AppConfig;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.view.foodrec.FoodRecDetailFragment;
import com.wangqing.chilemecilent.webapi.CommentApi;
import com.wangqing.chilemecilent.webapi.FoodRecApi;
import com.wangqing.chilemecilent.webapi.LikeFavoriteAttentionApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRecDetailViewModel extends AndroidViewModel {

    private MutableLiveData<FoodRecDetailDto> info;
    private MutableLiveData<String> comment;

    private AccountManager accountManager;

    private FoodRecApi foodRecApi;
    private LikeFavoriteAttentionApi likeFavoriteAttentionApi;

    private FRDSelDto frdSelDto;

    /**
     * 构造方法
     * @param application
     */
    public FoodRecDetailViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
        foodRecApi = RetrofitHandle.getInstance().getRetrofit().create(FoodRecApi.class);
        likeFavoriteAttentionApi = RetrofitHandle.getInstance().getRetrofit().create(LikeFavoriteAttentionApi.class);
    }

    /**setter & getter**/
    public MutableLiveData<FoodRecDetailDto> getInfo() {
        if (info == null){
            info = new MutableLiveData<>();
            getDetailByFRDSel();
        }
        return info;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public void setFrdSelDto(FRDSelDto frdSelDto) {
        this.frdSelDto = frdSelDto;
    }

    public MutableLiveData<String> getComment() {
        if (comment == null){
            comment = new MutableLiveData<>();
            comment.setValue("");
        }
        return comment;
    }

    /**web api**/
    public void getDetailByFRDSel(){
        Call<CommonResult<FoodRecDetailDto>> task = foodRecApi.detailByFRDSel(frdSelDto, accountManager.getToken());
        task.enqueue(new Callback<CommonResult<FoodRecDetailDto>>() {
            @Override
            public void onResponse(Call<CommonResult<FoodRecDetailDto>> call, Response<CommonResult<FoodRecDetailDto>> response) {
                if (response.code() == 200){
                    Log.d(AppConfig.TEST_TAG, "onResponse: " + response.body().getData());
                    getInfo().setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CommonResult<FoodRecDetailDto>> call, Throwable t) {

            }
        });

    }

    public void giveAFavorite(){
        Call<CommonResult<Object>> task = likeFavoriteAttentionApi.giveAFavorite(frdSelDto.getPostId(), frdSelDto.getUserId(), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {

            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });
    }

    public void giveALike(boolean flag){
        Call<CommonResult<Object>> task;
        if (flag){
            task = likeFavoriteAttentionApi.giveALike(new LikeReqDto(frdSelDto.getPostId(), AppConfig.LIKE_TYPE_POST, frdSelDto.getUserId()), accountManager.getToken());
        }else {
            task = likeFavoriteAttentionApi.giveALike(new LikeReqDto(frdSelDto.getPostId(), AppConfig.LIKE_TYPE_POST, frdSelDto.getUserId()), accountManager.getToken());
        }

        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {

            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });
    }

    public void attentionHandler(){
        Call<CommonResult<Object>> task = likeFavoriteAttentionApi.attentionHandler(frdSelDto.getPostUserId(), frdSelDto.getUserId(), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {

            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });
    }

    public void addComment(){
        CommentApi commentApi = RetrofitHandle.getInstance().getRetrofit().create(CommentApi.class);
        Call<CommonResult<Object>> task = commentApi.addComment(
                new CommentPostDto(frdSelDto.getPostId(), getComment().getValue(), frdSelDto.getUserId(), null), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {
                if (response.code() == 200){
                    Toast.makeText(getApplication(), "发送成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });
    }
}
