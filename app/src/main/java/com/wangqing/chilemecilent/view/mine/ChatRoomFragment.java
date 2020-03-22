package com.wangqing.chilemecilent.view.mine;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.adapter.ChatRoomAdapter;
import com.wangqing.chilemecilent.databinding.FragmentChatRoomBinding;
import com.wangqing.chilemecilent.object.dto.ChatDto;
import com.wangqing.chilemecilent.object.dto.UserInfoDto;
import com.wangqing.chilemecilent.utils.AppConfig;
import com.wangqing.chilemecilent.viewmodel.mine.ChatRoomViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatRoomFragment extends Fragment {

    private FragmentChatRoomBinding binding;

    private ChatRoomViewModel viewModel;

    private UserInfoDto userInfo;

    private TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence tmp;
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            tmp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (tmp.length() > 0) {
                binding.buttonSend.setEnabled(true);
                binding.buttonSend.setClickable(true);
                //binding.buttonSend.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorAccent));
            } else {
                binding.buttonSend.setEnabled(false);
                //binding.buttonSend.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.iconCover));
            }
        }
    };



    public ChatRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_room, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ChatRoomViewModel.class);

        binding.setLifecycleOwner(requireActivity());

        userInfo = (UserInfoDto) getArguments().get(AppConfig.MINE_TO_CHAT_ROOM_KEY);

        viewModel.setUserInfo(userInfo);

        return binding.getRoot();

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_chat_room, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.editSend.getText().toString();
                binding.editSend.setText("");
            }
        });
        binding.buttonSend.setEnabled(false);
        binding.editSend.setHorizontallyScrolling(false);
        binding.editSend.setMaxLines(3);
        binding.editSend.addTextChangedListener(mTextWatcher);

        ChatRoomAdapter adapter = new ChatRoomAdapter(userInfo.getUserId());

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        viewModel.getChatList().observe(getViewLifecycleOwner(), new Observer<List<ChatDto>>() {
            @Override
            public void onChanged(List<ChatDto> chatList) {
                adapter.setChatList(chatList);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
