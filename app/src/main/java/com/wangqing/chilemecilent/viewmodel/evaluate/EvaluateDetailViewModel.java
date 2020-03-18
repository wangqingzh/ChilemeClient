package com.wangqing.chilemecilent.viewmodel.evaluate;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wangqing.chilemecilent.object.ao.CESel;
import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.CommentBrowserDto;
import com.wangqing.chilemecilent.object.dto.CommentPostDto;
import com.wangqing.chilemecilent.object.dto.EvaluateDetailDto;
import com.wangqing.chilemecilent.object.dto.LikeReqDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.CommentApi;
import com.wangqing.chilemecilent.webapi.EvaluateApi;
import com.wangqing.chilemecilent.webapi.LikeFavoriteAttentionApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluateDetailViewModel extends AndroidViewModel {

    private MutableLiveData<EvaluateDetailDto> info;

    private MutableLiveData<List<CommentBrowserDto>> commentList;

    /* 从上一个页面传来的查询包装类 */
    private CESel ceSel;

    private AccountManager accountManager;

    private LikeFavoriteAttentionApi likeFavoriteAttentionApi;




    /**
     * 构造方法
     * @param application
     */
    public EvaluateDetailViewModel(@NonNull Application application) {
        super(application);
        accountManager = AccountManager.getInstance(application);
    }


    /* setter & getter */
    public MutableLiveData<EvaluateDetailDto> getInfo() {
        if (info == null){
            info = new MutableLiveData<>();
            getEvaluateDetailFromServer();
        }
        return info;
    }

    public MutableLiveData<List<CommentBrowserDto>> getCommentList() {
        if (commentList == null){
            commentList = new MutableLiveData<>();
        }
        return commentList;
    }

    public void setCeSel(CESel ceSel) {
        this.ceSel = ceSel;
    }


    public AccountManager getAccountManager() {
        return accountManager;
    }

    /* web api */
    public void getEvaluateDetailFromServer(){
        EvaluateApi evaluateApi = RetrofitHandle.getInstance().getRetrofit().create(EvaluateApi.class);
        Map<String, Integer> map = new HashMap<>();
        map.put("postId", ceSel.getPostId());
        map.put("postUserId", ceSel.getPostUserId());
        map.put("userId", ceSel.getUserId());
        Call<CommonResult<EvaluateDetailDto>> task = evaluateApi.getEvaluateDetail(map, accountManager.getToken());
        task.enqueue(new Callback<CommonResult<EvaluateDetailDto>>() {
            @Override
            public void onResponse(Call<CommonResult<EvaluateDetailDto>> call, Response<CommonResult<EvaluateDetailDto>> response) {
                if (response.code() == 200){
                    getInfo().setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CommonResult<EvaluateDetailDto>> call, Throwable t) {

            }
        });
    }

    public void giveAFavorite() {
        Call<CommonResult<Object>> task = likeFavoriteAttentionApi.giveAFavorite(ceSel.getPostId(), ceSel.getUserId(), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {

            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });
    }


    public void giveALike(LikeReqDto likeReqDto) {
        likeReqDto.setUserId(accountManager.getUser().getUserId());
        Call<CommonResult<Object>> task;
        task = likeFavoriteAttentionApi.giveALike(likeReqDto, accountManager.getToken());

        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {

            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });
    }

    public void attentionHandler() {
        Call<CommonResult<Object>> task = likeFavoriteAttentionApi.attentionHandler(ceSel.getPostUserId(), ceSel.getUserId(), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {

            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });
    }

    public void addComment(String text) {
        CommentApi commentApi = RetrofitHandle.getInstance().getRetrofit().create(CommentApi.class);
        Call<CommonResult<Object>> task = commentApi.addComment(
                new CommentPostDto(ceSel.getPostId(), text, ceSel.getUserId(), null), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {
                if (response.code() == 200) {
                    Toast.makeText(getApplication(), "发送成功", Toast.LENGTH_SHORT).show();
                    getComment();
                }
            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });
    }

    public void getComment() {
        CommentApi commentApi = RetrofitHandle.getInstance().getRetrofit().create(CommentApi.class);
        Call<CommonResult<List<CommentBrowserDto>>> task = commentApi.getComment(ceSel.getPostId(), ceSel.getUserId(), accountManager.getToken());
        task.enqueue(new Callback<CommonResult<List<CommentBrowserDto>>>() {
            @Override
            public void onResponse(Call<CommonResult<List<CommentBrowserDto>>> call, Response<CommonResult<List<CommentBrowserDto>>> response) {
                if (response.code() == 200) {
                    commentList.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CommonResult<List<CommentBrowserDto>>> call, Throwable t) {

            }
        });
    }

}
