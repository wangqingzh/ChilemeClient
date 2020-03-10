package com.wangqing.chilemecilent.view.evaluate;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentEvaluateBinding;
import com.wangqing.chilemecilent.databinding.FragmentEvaluatePostBinding;
import com.wangqing.chilemecilent.viewmodel.evaluate.EvaluatePostViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluatePostFragment extends Fragment {

    private FragmentEvaluatePostBinding binding;
    private EvaluatePostViewModel viewModel;


    public EvaluatePostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_evaluate_post, container, false);
        viewModel = new ViewModelProvider(this).get(EvaluatePostViewModel.class);
        binding.setData(viewModel);

        binding.setLifecycleOwner(requireActivity());


        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_evaluate_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);







    }
}
