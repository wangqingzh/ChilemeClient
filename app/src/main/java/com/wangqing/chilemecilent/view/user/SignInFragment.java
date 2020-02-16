package com.wangqing.chilemecilent.view.user;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentSignInBinding;
import com.wangqing.chilemecilent.object.ao.CommonResult;
import com.wangqing.chilemecilent.object.ao.User;
import com.wangqing.chilemecilent.object.dto.TokenDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.RetrofitHandle;
import com.wangqing.chilemecilent.webapi.UserApi;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * SignInFragment
 * 在这里处理token相关的逻辑
 */
public class SignInFragment extends Fragment implements View.OnClickListener{

    private FragmentSignInBinding binding;

    private UserApi userApi = RetrofitHandle.getInstance().getRetrofit().create(UserApi.class);

    private String userName;
    private String passWord;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container,false);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.textViewSignUp.setOnClickListener(this);

        binding.textViewForgetPassword.setOnClickListener(this);

        binding.buttonSignIn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        NavController controller = Navigation.findNavController(v);
        switch (v.getId()){
            case R.id.textViewSignUp:
                controller.navigate(R.id.action_signInFragment_to_signUpFragment);
                break;
            case R.id.textViewForgetPassword:
                controller.navigate(R.id.action_signInFragment_to_forgetPasswordFragment);
                break;
            case R.id.buttonSignIn:
                signIn();
                break;
        }
    }



    /**
     * 登录
     */
    private void signIn() {
        userName = binding.editTextUserName.getText().toString();
        passWord = binding.editTextPassWord.getText().toString();


        Call<TokenDto> task = userApi.getToken(userName, passWord);
        task.enqueue(new Callback<TokenDto>() {
            @Override
            public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                if (response.code() == 200){
                    TokenDto token = response.body();
                    signInSuccess(token);
                }else {
                    Toast.makeText(requireContext(), "用户或密码错误", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TokenDto> call, Throwable t) {
                Toast.makeText(requireContext(), "用户或密码错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 登录成功后续
     */
    private void signInSuccess(TokenDto token){
        AccountManager accountManager = AccountManager.getInstance(requireActivity().getApplication());
        Call<CommonResult<Integer>> task = userApi.getUserId(userName);
        task.enqueue(new Callback<CommonResult<Integer>>() {
            @Override
            public void onResponse(Call<CommonResult<Integer>> call, Response<CommonResult<Integer>> response) {
                if (response.code() == 200){
                    User user = new User();
                    user.setUserId(response.body().getData());  // 设置用户id
                    user.setAccess_token(token.getAccess_token());  // 设置用户token
                    user.setRefresh_token(token.getAccess_token()); // 设置用户刷新token
                    user.setExpires_in(LocalDateTime.now().plusSeconds(token.getExpires_in())); // 设置过期时间

                    accountManager.setOnline(true);
                    accountManager.setToken(token.getAccess_token());
                    accountManager.setUser(user);

                    Toast.makeText(requireContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    NavController controller = NavHostFragment.findNavController(SignInFragment.this);
                    controller.navigateUp();
                }
            }

            @Override
            public void onFailure(Call<CommonResult<Integer>> call, Throwable t) {

            }
        });
    }
}
