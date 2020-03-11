package com.wangqing.chilemecilent.view.evaluate;

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
import com.wangqing.chilemecilent.adapter.EvaluateBrowserAdapter;
import com.wangqing.chilemecilent.databinding.FragmentEvaluateBrowserBinding;
import com.wangqing.chilemecilent.object.dto.EvaluateBrowserDto;
import com.wangqing.chilemecilent.viewmodel.evaluate.EvaluateBrowserViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluateBrowserFragment extends Fragment {

    private FragmentEvaluateBrowserBinding binding;
    private EvaluateBrowserViewModel viewModel;

    private int hallId;

    public EvaluateBrowserFragment(int hallId) {
        // Required empty public constructor
        this.hallId = hallId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_evaluate_browser, container, false);

        viewModel = new ViewModelProvider(this).get(EvaluateBrowserViewModel.class);

        viewModel.setHallId(hallId);

        binding.setLifecycleOwner(this);


        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_evaluate_browser, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        EvaluateBrowserAdapter adapter = new EvaluateBrowserAdapter(requireActivity(), viewModel);


        viewModel.getEvaluateList().observe(getViewLifecycleOwner(), new Observer<List<EvaluateBrowserDto>>() {
            @Override
            public void onChanged(List<EvaluateBrowserDto> evaluateList) {
                adapter.setEvaluateList(evaluateList);
                adapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });

        binding.recyclerView.setAdapter(adapter);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getEvaluateBrowserFromServer();
            }
        });


    }
}
