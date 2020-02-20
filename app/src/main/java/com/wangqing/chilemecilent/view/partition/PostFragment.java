package com.wangqing.chilemecilent.view.partition;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.databinding.FragmentPostBinding;
import com.wangqing.chilemecilent.viewmodel.partition.PostViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment implements AdapterView.OnItemSelectedListener,
        RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    private final String TAG = this.getClass().toString();

    private FragmentPostBinding binding;
    private PostViewModel postViewModel;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false);

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);

        binding.setLifecycleOwner(requireActivity());
        binding.setData(postViewModel);

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    /**
     * 初始化界面
     */
    private void initView() {
        // spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.post_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerPartition.setAdapter(adapter);
        binding.spinnerPartition.setOnItemSelectedListener(this);
        // ratingBar
        binding.ratingBarRecommend.setOnRatingBarChangeListener(this);

        binding.buttonSelectImage.setOnClickListener(this);
        binding.buttonPost.setOnClickListener(this);
    }

    /**
     * 下拉框 spinner 回调
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        postViewModel.getPartitionId().setValue(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * ratingBar 变化回调
     * @param ratingBar
     * @param rating
     * @param fromUser
     */
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        binding.scoreView.setText(String.valueOf(rating));
        postViewModel.getRecommendScore().setValue(rating);
    }

    /**
     * 按钮点击 回调
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSelectImage: // 选择图片
                break;
            case R.id.buttonPost: // 发布 先传递信息 图片再发
                break;
        }
    }
}
