package com.wangqing.chilemecilent.view.message;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.adapter.MessageReplyAdapter;
import com.wangqing.chilemecilent.databinding.FragmentMessageReplyBinding;
import com.wangqing.chilemecilent.object.dto.MessageReplyDto;
import com.wangqing.chilemecilent.viewmodel.message.MessageViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageReplyFragment extends Fragment {

    private FragmentMessageReplyBinding binding;
    private MessageViewModel viewModel;

    public MessageReplyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message_reply, container, false);
        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        binding.setLifecycleOwner(requireActivity());

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_message_reply, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.swipeRefreshLayout.setRefreshing(true);

        MessageReplyAdapter adapter = new MessageReplyAdapter(requireContext());

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.getReplyList().observe(getViewLifecycleOwner(), new Observer<List<MessageReplyDto>>() {
            @Override
            public void onChanged(List<MessageReplyDto> replyList) {
                adapter.setReplyList(replyList);
                adapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swipeRefreshLayout.setRefreshing(true);
                viewModel.getReplyListFromServer();
            }
        });
    }
}
