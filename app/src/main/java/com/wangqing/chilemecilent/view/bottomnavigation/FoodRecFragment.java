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
import android.widget.Toast;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentFoodRecBinding;
import com.wangqing.chilemecilent.utils.AccountManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodRecFragment extends Fragment implements View.OnClickListener {

    private FragmentFoodRecBinding binding;

    public FoodRecFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_rec, container, false);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_partition, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        binding.floatingActionButton.setOnClickListener(this);

    }

    /**
     * 点击处理实现方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        NavController controller = Navigation.findNavController(v);
        switch (v.getId()) {
            case R.id.floatingActionButton:
                if (!AccountManager.getInstance(requireActivity().getApplication()).isOnline()) {
                    Toast.makeText(requireContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    controller.navigate(R.id.action_foodRecFragment_to_signInFragment);
                } else {
                    controller.navigate(R.id.action_foodRecFragment_to_foodRecPostFragment);
                }
                break;
            default:
                break;
        }
    }
}
