package com.wangqing.chilemecilent.view.foodrec;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.adapter.FoodRecBrowserAdapter;
import com.wangqing.chilemecilent.databinding.FragmentFoodRecBrowserBinding;
import com.wangqing.chilemecilent.object.dto.FoodRecBrowserDto;
import com.wangqing.chilemecilent.viewmodel.foodRec.FoodRecBrowserViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodRecBrowserFragment extends Fragment {

    private FragmentFoodRecBrowserBinding binding;
    private FoodRecBrowserViewModel viewModel;

    private int classifyId; // 从上一页传递过来的分类id


    public FoodRecBrowserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_rec_browser, container, false);

        viewModel = new ViewModelProvider(this).get(FoodRecBrowserViewModel.class);

        binding.setLifecycleOwner(requireActivity());
        binding.setData(viewModel);

        classifyId = getArguments().getInt("REC_CLASSIFY_ID");
        viewModel.setClassifyId(classifyId);

        return binding.getRoot();

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_food_rec_browser, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FoodRecBrowserAdapter adapter = new FoodRecBrowserAdapter();

        viewModel.getFoodRecList().observe(getViewLifecycleOwner(), new Observer<List<FoodRecBrowserDto>>() {
            @Override
            public void onChanged(List<FoodRecBrowserDto> foodRecBrowserDtos) {
                adapter.setFoodRecList(foodRecBrowserDtos);
                adapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });

        if (viewModel.getFoodRecList().getValue() == null){
            viewModel.getFoodRecListFromServer();
        }

        binding.recyclerView.setAdapter(adapter);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getFoodRecListFromServer();
            }
        });

    }
}
