package com.wangqing.chilemecilent.view.bottomnavigation;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentMineBinding;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.viewmodel.bottomnavigation.MineViewModel;

/**
 * MineFragment 底部导航之一的我的页面
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private final String TAG = this.getClass().toString();
    private FragmentMineBinding binding;
    private MineViewModel mineViewModel;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (!AccountManager.getInstance(requireActivity().getApplication()).isOnline()) {
            NavController controller = NavHostFragment.findNavController(this);
            controller.navigate(R.id.action_mineFragment_to_signInFragment);
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        mineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        Log.d(TAG, "onCreateView: " + mineViewModel);
        binding.setData(mineViewModel);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MineViewModel mineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        Log.d(TAG, "onActivityCreated: " + mineViewModel);


        binding.settingLayout.setOnClickListener(this);
        binding.infoLayout.setOnClickListener(this);
        binding.buttonSpace.setOnClickListener(this);
    }

    /**
     * 处理该页面的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        NavController controller = Navigation.findNavController(v);
        switch (v.getId()) {
            case R.id.buttonSpace:
                controller.navigate(R.id.action_mineFragment_to_spaceFragment);
                break;
            case R.id.settingLayout:
                controller.navigate(R.id.action_mineFragment_to_settingFragment);
                break;
            case R.id.infoLayout:
                controller.navigate(R.id.action_mineFragment_to_editFragment);
                break;
            default:
                break;

        }

    }
}
