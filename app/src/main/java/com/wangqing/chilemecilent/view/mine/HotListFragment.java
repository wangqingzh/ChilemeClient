package com.wangqing.chilemecilent.view.mine;

import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentHotListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotListFragment extends Fragment {

    private FragmentHotListBinding binding;


    public HotListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hot_list, container, false);
        binding.setLifecycleOwner(requireActivity());
        // Inflate the layout for this fragment

        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_hot_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }
}
