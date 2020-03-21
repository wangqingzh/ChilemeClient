package com.wangqing.chilemecilent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.object.dto.ChatDto;
import com.wangqing.chilemecilent.utils.AppConfig;

import java.util.List;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.Holder> {

    private List<ChatDto> chatList;

    private Integer userId;



    public ChatRoomAdapter(Integer userId) {
        this.userId = userId;
    }

    public void setChatList(List<ChatDto> chatList) {
        this.chatList = chatList;
    }




    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (userId == viewType){
            itemView = layoutInflater.inflate(R.layout.chat_room_holder_me, parent, false);
        }else {
            itemView = layoutInflater.inflate(R.layout.chat_room_holder_other, parent, false);
        }
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ChatDto info = chatList.get(position);

        Glide.with(holder.itemView)
                .load(AppConfig.BASE_URL + info.getUserAvatar())
                .into(holder.userAvatar);

        holder.userName.setText(info.getUserName());

        holder.msg.setText(info.getMsg());

        holder.time.setText(info.getTime().toString());
    }

    @Override
    public int getItemCount() {
        return chatList != null ? chatList.size() : 0;
    }


    @Override
    public int getItemViewType(int position) {
        return chatList.get(position).getUserId();
    }




    static class  Holder extends  RecyclerView.ViewHolder{

        ImageView userAvatar;
        TextView userName;
        TextView msg;
        TextView time;

        public Holder(@NonNull View itemView) {
            super(itemView);
            userAvatar = itemView.findViewById(R.id.userAvatar);
            userName = itemView.findViewById(R.id.userName);
            msg = itemView.findViewById(R.id.msg);
            time = itemView.findViewById(R.id.time);
        }
    }
}
