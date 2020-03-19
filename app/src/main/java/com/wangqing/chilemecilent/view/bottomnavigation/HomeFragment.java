package com.wangqing.chilemecilent.view.bottomnavigation;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.view.home.HomeAttentionFragment;
import com.wangqing.chilemecilent.view.home.HomeRecommendFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutHome);
        ViewPager2 viewPager2 = getActivity().findViewById(R.id.viewPageHome);


        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new HomeRecommendFragment();
                    case 1:
                        return new HomeAttentionFragment();
                }
                return null;
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("推荐");
                    break;
                case 1:
                    tab.setText("关注");
                    break;
            }
        }).attach();
    }
}
