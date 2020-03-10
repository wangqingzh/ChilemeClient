package com.wangqing.chilemecilent.view.evaluate;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentHallBrowserBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class HallBrowserFragment extends Fragment {

    private FragmentHallBrowserBinding binding;

    private int hallClassify;

    public HallBrowserFragment(int hallClassify) {
        // Required empty public constructor
        this.hallClassify = hallClassify;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hall_browser, container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_hall_browser, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.textView9.setText("" + hallClassify);
    }
}
