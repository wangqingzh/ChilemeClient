package com.wangqing.chilemecilent.view.mine;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.adapter.FoodGalleryAdapter;
import com.wangqing.chilemecilent.databinding.FragmentFoodGalleryBinding;
import com.wangqing.chilemecilent.object.dto.FoodGalleryDto;
import com.wangqing.chilemecilent.viewmodel.mine.FoodGalleryViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodGalleryFragment extends Fragment {

    private FragmentFoodGalleryBinding binding;
    private FoodGalleryViewModel viewModel;

    public FoodGalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_gallery, container, false);
        binding.setLifecycleOwner(requireActivity());
        viewModel = new ViewModelProvider(this).get(FoodGalleryViewModel.class);

        // Inflate the layout for this fragment
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_food_gallery, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FoodGalleryAdapter adapter = new FoodGalleryAdapter();

        viewModel.getGalleryList().observe(getViewLifecycleOwner(), new Observer<List<FoodGalleryDto>>() {
            @Override
            public void onChanged(List<FoodGalleryDto> galleryDto) {
                adapter.setPhotoItemList(galleryDto);
                adapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });

        if (viewModel.getGalleryList().getValue() == null) {
            viewModel.getGalleryListFromServer();
        }


        binding.recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getGalleryListFromServer();
            }
        });

    }
}
