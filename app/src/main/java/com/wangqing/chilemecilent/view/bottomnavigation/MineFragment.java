package com.wangqing.chilemecilent.view.bottomnavigation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentMineBinding;
import com.wangqing.chilemecilent.object.dto.UserInfoDto;
import com.wangqing.chilemecilent.utils.AccountManager;
import com.wangqing.chilemecilent.utils.AppConfig;
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
        mineViewModel = new ViewModelProvider(requireActivity()).get(MineViewModel.class);
        binding.setData(mineViewModel);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mineViewModel.getUserInfoFromServer();
        initView();
        setAvatar();

    }

    /**
     * 初始化页面
     */
    private void initView() {
        binding.historyLayout.setOnClickListener(this);
        binding.myFavoriteLayout.setOnClickListener(this);
        binding.infoLayout.setOnClickListener(this);
        binding.settingLayout.setOnClickListener(this);

        binding.buttonSpace.setOnClickListener(this);
        binding.chatRoomLayout.setOnClickListener(this);
        binding.hotListLayout.setOnClickListener(this);
        binding.foodGalleryLayout.setOnClickListener(this);


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
            case R.id.chatRoomLayout:
                Bundle bundle = new Bundle();
                bundle.putSerializable(AppConfig.MINE_TO_CHAT_ROOM_KEY, mineViewModel.getInfo().getValue());
                controller.navigate(R.id.action_mineFragment_to_chatRoomFragment, bundle);
                break;
            case R.id.foodGalleryLayout:
                controller.navigate(R.id.action_mineFragment_to_foodGalleryFragment);
                break;
            case R.id.hotListLayout:
                controller.navigate(R.id.action_mineFragment_to_hotListFragment);
                break;
            default:
                break;
        }

    }

    /**
     * 设置头像
     */
    private void setAvatar() {
        mineViewModel.getInfo().observe(getViewLifecycleOwner(), new Observer<UserInfoDto>() {
            @Override
            public void onChanged(UserInfoDto userInfoDto) {
                Glide.with(MineFragment.this)
                        .load(AppConfig.BASE_URL + userInfoDto.getAvatarUrl())
                        .error(R.drawable.ic_avatar_default)
                        .into(binding.avatar);
            }
        });

    }

}
