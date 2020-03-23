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
import com.wangqing.chilemecilent.object.dto.MessageLikeDto;
import com.wangqing.chilemecilent.utils.AppConfig;
import com.wangqing.chilemecilent.utils.RelativeDateFormat;
import com.wangqing.chilemecilent.utils.StringUtil;

import java.util.List;

public class MessageLikeAdapter extends RecyclerView.Adapter<MessageLikeAdapter.Holder> {

    private List<MessageLikeDto> likeList;

    /**
     * 构造方法
     */
    public MessageLikeAdapter(){

    }

    /* setter & getter */

    public void setLikeList(List<MessageLikeDto> likeList) {
        this.likeList = likeList;
    }



    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.message_like_holder, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        MessageLikeDto info = likeList.get(position);
        Glide.with(holder.itemView)
            .load(AppConfig.BASE_URL + info.getLikeUserAvatar())
            .into(holder.likeUserAvatar);
        holder.likeUserName.setText(info.getLikeUserName());
        holder.likeTime.setText(RelativeDateFormat.format(info.getLikeTime()));
        holder.content.setText(StringUtil.omitString(info.getContent(), 10));
        if (info.getLikeType() == MessageLikeDto.LIKE_POST){
            holder.declare.setText("赞了我的帖子");
        }else if (info.getLikeType() == MessageLikeDto.LIKE_REPLY){
            holder.declare.setText("赞了我的评论");
        }
    }

    @Override
    public int getItemCount() {
        return likeList != null ? likeList.size() : 0;
    }





    static class Holder extends RecyclerView.ViewHolder{
        ImageView likeUserAvatar;
        TextView likeUserName;
        TextView likeTime;
        TextView declare;
        TextView content;
        public Holder(@NonNull View itemView) {
            super(itemView);
            likeUserAvatar = itemView.findViewById(R.id.likeUserAvatar);
            likeUserName = itemView.findViewById(R.id.likeUserName);
            likeTime = itemView.findViewById(R.id.likeTime);
            declare = itemView.findViewById(R.id.declare);
            content = itemView.findViewById(R.id.content);
        }
    }
}
