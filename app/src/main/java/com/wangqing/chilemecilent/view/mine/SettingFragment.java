package com.wangqing.chilemecilent.view.mine;


import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentSettingBinding;
import com.wangqing.chilemecilent.utils.AccountManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {
    private FragmentSettingBinding binding;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView(); // 初始化布局

    }

    private void initView() {
        binding.settingPhone.setOnClickListener(this);
        binding.settingModifyPassword.setOnClickListener(this);
        binding.settingAbout.setOnClickListener(this);
        binding.buttonLogOut.setOnClickListener(this);
    }

    /**
     * 处理点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        NavController controller = Navigation.findNavController(v);
        switch (v.getId()) {
            case R.id.settingPhone:
                break;
            case R.id.settingModifyPassword:
                controller.navigate(R.id.action_settingFragment_to_modifyPasswordFragment);
                break;
            case R.id.settingAbout:
                settingAbout();
                break;
            case R.id.buttonLogOut:
                logOut();
                controller.navigateUp();
                break;
            default:
                break;
        }
    }

    /**
     * 注销事件
     */
    private void logOut() {
        AccountManager accountManager = AccountManager.getInstance(requireActivity().getApplication());
        accountManager.logOut();
    }


    private void settingAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        TextView textView = new TextView(requireContext());
        textView.setText("吃了么客户端\n2020");
        builder.setView(textView);
        //builder.setTitle();
        builder.show();
    }
}
