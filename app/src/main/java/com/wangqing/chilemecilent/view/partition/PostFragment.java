package com.wangqing.chilemecilent.view.partition;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentPostBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private final String TAG = this.getClass().toString();

    private FragmentPostBinding binding;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false);
        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.post_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerPartition.setAdapter(adapter);
        binding.spinnerPartition.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemSelected: " + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
