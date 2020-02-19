package com.wangqing.chilemecilent.view.home;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentHomeRecommendBinding;
import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.UploadFileDto;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.FileApi;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeRecommendFragment extends Fragment {

    private FragmentHomeRecommendBinding binding;

    public HomeRecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_recommend, container, false);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home_recommend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get();
            }
        });
    }
    private void get(){
        FileApi fileApi = RetrofitHandle.getInstance().getRetrofit().create(FileApi.class);
        Map<String, Object> params = new HashMap<>();
        UploadFileDto uploadFileDto = new UploadFileDto(UploadFileDto.EVALUATION_IMAGE, 1234);
        params.put("upInfo", uploadFileDto);
        Call<CommonResult<Object>> task = fileApi.uploadFile(params, null);
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {
                Log.d("tag", "onResponse: " + response.code());
            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {

            }
        });
    }
}
