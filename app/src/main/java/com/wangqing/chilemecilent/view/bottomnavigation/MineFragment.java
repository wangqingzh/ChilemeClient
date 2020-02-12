package com.wangqing.chilemecilent.view.bottomnavigation;


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

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentMineBinding;
import com.wangqing.chilemecilent.utils.AccountManager;

/**
 * MineFragment 底部导航之一的我的页面
 */
public class MineFragment extends Fragment  implements View.OnClickListener{

    private final String TAG = this.getClass().toString();
    private FragmentMineBinding binding;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(!AccountManager.getInstance(requireActivity().getApplication()).isOnline()){
            NavController controller = NavHostFragment.findNavController(this);
            controller.navigate(R.id.action_mineFragment_to_signInFragment);
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.settingLayout.setOnClickListener(this);
    }

    /**
     * 处理该页面的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        NavController controller = Navigation.findNavController(v);
        switch (v.getId()){
            case R.id.settingLayout:
                controller.navigate(R.id.action_mineFragment_to_settingFragment);
                break;
        }

    }
}
