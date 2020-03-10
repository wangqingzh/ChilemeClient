package com.wangqing.chilemecilent.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.object.dto.CommentBrowserDto;
import com.wangqing.chilemecilent.object.dto.LikeReqDto;
import com.wangqing.chilemecilent.utils.AppConfig;
import com.wangqing.chilemecilent.utils.RelativeDateFormat;
import com.wangqing.chilemecilent.viewmodel.foodRec.FoodRecDetailViewModel;

import java.util.List;

public class FoodRecDetailAdapter extends RecyclerView.Adapter<FoodRecDetailAdapter.ViewHolder> {

    private List<CommentBrowserDto> commentList;
    private Activity activity;
    private FoodRecDetailViewModel viewModel;


    public FoodRecDetailAdapter(Activity activity, FoodRecDetailViewModel viewModel) {
        this.activity = activity;
        this.viewModel = viewModel;
    }

    public void setCommentList(List<CommentBrowserDto> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.food_rec_comment_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentBrowserDto info = commentList.get(position);

        holder.userName.setText(info.getUserName());
        holder.likeNumber.setText(String.valueOf(info.getLikeNumber()));
        holder.floorAndDate.setText((position + 1) + "楼 | " + RelativeDateFormat.format(info.getWriteTime()));
        holder.comment.setText(info.getComment());

        Glide.with(holder.itemView)
                .load(AppConfig.BASE_URL + info.getUserAvatar())
                .into(holder.userAvatar);

        // 点赞处理
        holder.buttonLike.init(activity);
        if (info.isLikeStatus()){
            holder.buttonLike.setChecked(true);
        }else {
            holder.buttonLike.setChecked(false);
        }
        holder.buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.buttonLike.isChecked()){
                    info.setLikeNumber(info.getLikeNumber() + 1);
                    info.setLikeStatus(!info.isLikeStatus());
                    commentList.set(position, info);
                    FoodRecDetailAdapter.this.notifyDataSetChanged();
                    // 更新服务端
                    viewModel.giveALike(new LikeReqDto(info.getCommentId(), AppConfig.LIKE_TYPE_COMMENT, null));
                }else {
                    info.setLikeNumber(info.getLikeNumber() - 1);
                    info.setLikeStatus(!info.isLikeStatus());
                    commentList.set(position, info);
                    FoodRecDetailAdapter.this.notifyDataSetChanged();
                    viewModel.giveALike(new LikeReqDto(info.getCommentId(), AppConfig.LIKE_TYPE_COMMENT, null));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return commentList != null ? commentList.size() : 0;
    }



    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView userAvatar;
        TextView userName;
        TextView floorAndDate;
        ShineButton buttonLike;
        TextView likeNumber;
        TextView comment;
        TextView reply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userAvatar = itemView.findViewById(R.id.userAvatar);
            userName = itemView.findViewById(R.id.userName);
            floorAndDate = itemView.findViewById(R.id.floorAndDate);
            buttonLike = itemView.findViewById(R.id.buttonLike);
            likeNumber = itemView.findViewById(R.id.likeNumber);
            comment = itemView.findViewById(R.id.comment);
            reply = itemView.findViewById(R.id.reply);
        }
    }

}
