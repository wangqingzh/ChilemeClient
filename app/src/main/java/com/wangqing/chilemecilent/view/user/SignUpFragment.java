package com.wangqing.chilemecilent.view.user;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentSignUpBinding;
import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.SignUpDto;
import com.wangqing.chilemecilent.singleton.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.UserApi;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 注册页面
 *
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private final  String TAG = this.getClass().toString();

    FragmentSignUpBinding binding;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // 数据绑定
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(requireContext(), binding.editTextPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                SignUpDto signUpDto = SignUpDto.of(binding.editTextUserName.getText().toString(), binding.editTextPassword.getText().toString(),
                        binding.editTextSecurityQuestion.getText().toString(), binding.editTextSecurityAnswer.getText().toString());
                signUp(signUpDto);
                //                if (signUp(signUpDto)){
//                    NavController controller = Navigation.findNavController(v);
//                    controller.navigateUp();
//                }
            }
        });
    }




//    // 注册逻辑 向服务端发送数据数据
//    public boolean signUp(SignUpDto signUpDto){
//        UserApi userApi = RetrofitHandle.getInstance().getRetrofit().create(UserApi.class);
//        CommonResult<String> commonResult = null;
//        Call<CommonResult<String>> task = userApi.signUp(signUpDto);
//        try {
//            commonResult = task.execute().body();
//        } catch (IOException e) {
//            Log.d(TAG, "signUp: " + e.getMessage());
//        }
//        if (commonResult == null){
//            Toast.makeText(requireContext(), "网络出错，请重试！", Toast.LENGTH_SHORT).show();
//            return false;
//        }else if (commonResult.getCode().equals(2005)){
//            Toast.makeText(requireContext(), "用户已存在！", Toast.LENGTH_SHORT).show();
//            return false;
//        }else if (commonResult.getCode().equals(1)){
//            Toast.makeText(requireContext(), "注册成功，请登录！", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        return false;
//    }


    // 注册逻辑 向服务端发送数据数据
    public void signUp(SignUpDto signUpDto){
        UserApi userApi = RetrofitHandle.getInstance().getRetrofit().create(UserApi.class);
        CommonResult<String> commonResult = null;
        Call<CommonResult<String>> task = userApi.signUp(signUpDto);
        task.enqueue(new Callback<CommonResult<String>>() {
            @Override
            public void onResponse(Call<CommonResult<String>> call, Response<CommonResult<String>> response) {
                CommonResult<String> result = response.body();
                Log.d(TAG, "onResponse: " + result);
            }

            @Override
            public void onFailure(Call<CommonResult<String>> call, Throwable t) {

            }
        });
    }

}
