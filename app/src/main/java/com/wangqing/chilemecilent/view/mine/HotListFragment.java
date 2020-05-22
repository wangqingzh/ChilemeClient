package com.wangqing.chilemecilent.view.mine;

import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.adapter.HotListAdapter;
import com.wangqing.chilemecilent.databinding.FragmentHotListBinding;
import com.wangqing.chilemecilent.object.dto.FoodGalleryDto;
import com.wangqing.chilemecilent.object.dto.HotListDto;
import com.wangqing.chilemecilent.viewmodel.mine.HotListViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotListFragment extends Fragment {

    private FragmentHotListBinding binding;

    private HotListViewModel viewModel;

    public HotListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hot_list, container, false);
        binding.setLifecycleOwner(requireActivity());
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(HotListViewModel.class);
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_hot_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HotListAdapter adapter = new HotListAdapter();

        viewModel.getHotList().observe(getViewLifecycleOwner(), new Observer<List<HotListDto>>() {
            @Override
            public void onChanged(List<HotListDto> hotListDtos) {
                adapter.setHotList(hotListDtos);
                adapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            }

        });

        if (viewModel.getHotList().getValue() == null) {
            viewModel.getHotListFromServer();
        }


        binding.recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getHotListFromServer();
            }
        });

    }
}
