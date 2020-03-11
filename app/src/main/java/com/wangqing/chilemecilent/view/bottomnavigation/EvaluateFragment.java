package com.wangqing.chilemecilent.view.bottomnavigation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayoutMediator;
import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentEvaluateBinding;
import com.wangqing.chilemecilent.view.evaluate.EvaluateBrowserFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluateFragment extends Fragment {

    private FragmentEvaluateBinding binding;


    public EvaluateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_evaluate, container, false);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_evaluate, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new EvaluateBrowserFragment(0);
                    case 1:
                        return new EvaluateBrowserFragment(1);
                    case 2:
                        return new EvaluateBrowserFragment(2);
                    case 3:
                        return new EvaluateBrowserFragment(3);

                }
                return null;
            }

            @Override
            public int getItemCount() {
                return 4;
            }
        });

        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("东苑");
                    break;
                case 1:
                    tab.setText("欣苑");
                    break;
                case 2:
                    tab.setText("西苑");
                    break;
                case 3:
                    tab.setText("北苑");
                    break;
            }
        }).attach();


        binding.buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_evaluateFragment_to_evaluatePostFragment);
            }
        });

    }
}
