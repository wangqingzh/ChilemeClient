package com.wangqing.chilemecilent.view.bottomnavigation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangqing.chilemecilent.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartitionFragment extends Fragment {


    public PartitionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_partition, container, false);
    }

}
