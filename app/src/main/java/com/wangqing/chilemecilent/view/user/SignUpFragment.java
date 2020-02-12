package com.wangqing.chilemecilent.view.user;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentSignUpBinding;
import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.dto.SignUpDto;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 注册页面
 * <p>
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private final String TAG = this.getClass().toString();

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
                SignUpDto signUpDto = SignUpDto.of(binding.editTextUserName.getText().toString(), binding.editTextPassword.getText().toString(),
                        binding.editTextSecurityQuestion.getText().toString(), binding.editTextSecurityAnswer.getText().toString());

                signUp(signUpDto);
            }
        });
    }


    // 注册逻辑 向服务端发送数据数据
    public void signUp(SignUpDto signUpDto) {
        UserApi userApi = RetrofitHandle.getInstance().getRetrofit().create(UserApi.class);

        Call<CommonResult<Object>> task = userApi.signUp(signUpDto);
        task.enqueue(new Callback<CommonResult<Object>>() {
            @Override
            public void onResponse(Call<CommonResult<Object>> call, Response<CommonResult<Object>> response) {
                CommonResult<Object> commonResult = response.body();
                if (commonResult.getCode() == 1){
                    Toast.makeText(requireContext(), "注册成功！即将跳转登录", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {  // 在等待中 用户提前返回 应用提出 *待解决*
                        @Override
                        public void run() {
                            NavController controller = NavHostFragment.findNavController(SignUpFragment.this);
                            controller.navigateUp();
                        }
                    }, 1000);
                }else if (commonResult.getCode() == 2005){
                    Toast.makeText(requireContext(), "该手机号已存在！", Toast.LENGTH_SHORT).show();
                    binding.editTextUserName.hasFocus();
                }
            }

            @Override
            public void onFailure(Call<CommonResult<Object>> call, Throwable t) {
                Toast.makeText(requireContext(), "注册失败，请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
