package com.wangqing.chilemecilent.adapter;

import android.app.Activity;
import android.text.TextUtils;
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
import com.wangqing.chilemecilent.object.dto.DynamicDto;
import com.wangqing.chilemecilent.object.dto.LikeReqDto;
import com.wangqing.chilemecilent.utils.AppConfig;
import com.wangqing.chilemecilent.utils.RelativeDateFormat;
import com.wangqing.chilemecilent.viewmodel.dynamic.DynamicViewModel;

import java.util.List;

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.Holder> {

    private List<DynamicDto> dynamicList;

    private Activity activity;

    private DynamicViewModel viewModel;

    /**
     * 构造方法
     * @param activity
     * @param viewModel
     */
    public DynamicAdapter(Activity activity, DynamicViewModel viewModel){
        this.activity = activity;
        this.viewModel = viewModel;
    }

    /* setter & getter  */

    public void setDynamicList(List<DynamicDto> dynamicList) {
        this.dynamicList = dynamicList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.dynamic_holder, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DynamicDto info = dynamicList.get(position);
        Glide.with(holder.itemView)
                .load(AppConfig.BASE_URL + info.getPostUserAvatar())
                .into(holder.userAvatar);
        holder.userName.setText(info.getPostUserName());

        holder.postTime.setText(RelativeDateFormat.format(info.getPostTime()));

        if (info.getPostType() == DynamicDto.POST_EVALUATE){
            holder.postType.setText("校园");
        }else if (info.getPostType() == DynamicDto.POST_FOOD_REC){
            holder.postType.setText("美食");
        }

        holder.headline.setText(info.getHeadline());
        if (!TextUtils.isEmpty(info.getPostImage())){
            Glide.with(holder.itemView)
                    .load(AppConfig.BASE_URL + info.getPostImage())
                    .into(holder.postImage);
        }
        holder.likeNumber.setText(info.getLikeNumber());
        holder.commentNumber.setText(info.getCommentNumber());



        if (info.isLikeStatus()){
            holder.buttonLike.setChecked(true);
        }else {
            holder.buttonLike.setChecked(false);
        }

        holder.buttonLike.init(activity);
        holder.buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.buttonLike.isChecked()){
                    // 更新界面
                    info.setLikeNumber(info.getLikeNumber() + 1);
                    info.setLikeStatus(!info.isLikeStatus());
                    dynamicList.set(position, info);
                    DynamicAdapter.this.notifyDataSetChanged();

                    // 更新服务端
                    viewModel.giveALike(LikeReqDto.of(info.getPostId(), AppConfig.LIKE_TYPE_POST));
                }else {
                    info.setLikeNumber(info.getLikeNumber() - 1);
                    info.setLikeStatus(!info.isLikeStatus());
                    dynamicList.set(position, info);
                    DynamicAdapter.this.notifyDataSetChanged();

                    viewModel.giveALike(LikeReqDto.of(info.getPostId(), AppConfig.LIKE_TYPE_POST));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dynamicList != null ? dynamicList.size() : 0;
    }




    static class Holder extends RecyclerView.ViewHolder{
        ShineButton buttonLike;
        ImageView userAvatar, postImage;
        TextView userName, postTime, headline, likeNumber, commentNumber, postType;
        public Holder(@NonNull View itemView) {
            super(itemView);
            buttonLike = itemView.findViewById(R.id.buttonLike);
            userAvatar = itemView.findViewById(R.id.userAvatar);
            postImage = itemView.findViewById(R.id.postImage);
            userName = itemView.findViewById(R.id.userName);
            postTime = itemView.findViewById(R.id.postTime);
            headline = itemView.findViewById(R.id.headline);
            likeNumber = itemView.findViewById(R.id.likeNumber);
            commentNumber = itemView.findViewById(R.id.commentNumber);
            postType = itemView.findViewById(R.id.postType);
        }
    }
}
