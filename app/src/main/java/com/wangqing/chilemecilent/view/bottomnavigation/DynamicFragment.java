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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.adapter.DynamicAdapter;
import com.wangqing.chilemecilent.databinding.FragmentDynamicBinding;
import com.wangqing.chilemecilent.object.dto.DynamicDto;
import com.wangqing.chilemecilent.viewmodel.dynamic.DynamicViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends Fragment {

    private FragmentDynamicBinding binding;
    private DynamicViewModel viewModel;

    public DynamicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dynamic, container, false);

        viewModel = new ViewModelProvider(this).get(DynamicViewModel.class);

        binding.setLifecycleOwner(requireActivity());


        return binding.getRoot();
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.swipeRefreshLayout.setRefreshing(true);

        DynamicAdapter adapter = new DynamicAdapter(requireActivity(), viewModel);

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        viewModel.getDynamicList().observe(getViewLifecycleOwner(), new Observer<List<DynamicDto>>() {
            @Override
            public void onChanged(List<DynamicDto> dynamicList) {
                adapter.setDynamicList(dynamicList);
                adapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swipeRefreshLayout.setRefreshing(true);
                viewModel.getDynamicListFromServer();
            }
        });


    }
}
