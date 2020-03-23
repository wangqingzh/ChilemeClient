package com.wangqing.chilemecilent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.object.dto.MessageReplyDto;
import com.wangqing.chilemecilent.utils.AppConfig;
import com.wangqing.chilemecilent.utils.RelativeDateFormat;
import com.wangqing.chilemecilent.utils.StringUtil;

import java.util.List;

public class MessageReplyAdapter extends RecyclerView.Adapter<MessageReplyAdapter.Holder> {

    private Context context;

    private List<MessageReplyDto> replyList;

    /**
     * 构造方法
     * @param context
     */
    public MessageReplyAdapter(Context context){
        this.context = context;
    }

    /* setter & getter */

    public void setReplyList(List<MessageReplyDto> replyList) {
        this.replyList = replyList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.message_reply_holder, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        MessageReplyDto info = replyList.get(position);

        Glide.with(holder.itemView)
                .load(AppConfig.BASE_URL + info.getCommentUserAvatar())
                .into(holder.userAvatar);
        holder.originalContent.setText(StringUtil.omitString(info.getOriginalContent(), 10));
        holder.replyContent.setText(info.getReplyContent());
        holder.time.setText(RelativeDateFormat.format(info.getCommentTime()));

        if (info.getCommentType() == MessageReplyDto.COMMENT_REPLY_TO_POST){
            holder.declare.setText(info.getCommentUserName() + "回复了我的帖子");
        }else if (info.getCommentType() == MessageReplyDto.COMMENT_REPLY_TO_REPLY){
            holder.declare.setText(info.getCommentUserName() + "回复了我的评论");
        }
    }

    @Override
    public int getItemCount() {
        return replyList != null ? replyList.size() : 0;
    }



    /**
     * holder
     */
    static class Holder extends RecyclerView.ViewHolder{

        ImageView userAvatar;
        TextView originalContent;
        TextView time;
        TextView replyContent;
        TextView declare;
        public Holder(@NonNull View itemView) {
            super(itemView);
            userAvatar = itemView.findViewById(R.id.userAvatar);
            originalContent = itemView.findViewById(R.id.originalContent);
            time = itemView.findViewById(R.id.time);
            replyContent = itemView.findViewById(R.id.replyContent);
            declare = itemView.findViewById(R.id.declare);
        }
    }
}
