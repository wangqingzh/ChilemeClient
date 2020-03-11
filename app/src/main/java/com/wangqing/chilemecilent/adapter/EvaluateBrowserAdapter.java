package com.wangqing.chilemecilent.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.object.dto.EvaluateBrowserDto;
import com.wangqing.chilemecilent.object.dto.LikeReqDto;
import com.wangqing.chilemecilent.utils.AppConfig;
import com.wangqing.chilemecilent.utils.RelativeDateFormat;
import com.wangqing.chilemecilent.viewmodel.evaluate.EvaluateBrowserViewModel;

import java.util.List;

public class EvaluateBrowserAdapter extends RecyclerView.Adapter<EvaluateBrowserAdapter.ViewHolder> {

    private List<EvaluateBrowserDto> evaluateList;

    private Activity activity;
    private EvaluateBrowserViewModel viewModel;

    public EvaluateBrowserAdapter(FragmentActivity activity, EvaluateBrowserViewModel viewModel) {
        this.activity = activity;
        this.viewModel = viewModel;
    }



    public void setEvaluateList(List<EvaluateBrowserDto> evaluateList) {
        this.evaluateList = evaluateList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.evaluate_browser_holder, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EvaluateBrowserDto info = evaluateList.get(position);

        holder.userName.setText(info.getUserName());
        holder.postTime.setText(RelativeDateFormat.format(info.getPostTime()));
        holder.postHeadline.setText(info.getPostHeadline());
        holder.storeNameDish.setText(info.getStoreNameDish());

        holder.likeNumber.setText(info.getLikeNumber().toString());
        holder.commentNumber.setText(info.getCommentNumber().toString());


        Glide.with(holder.itemView)
                .load(AppConfig.BASE_URL + info.getUserAvatar())
                .into(holder.userAvatar);

        if (info.getPostImageUrl() == null){
            holder.postImage.setVisibility(View.GONE);
        }else {
            Glide.with(holder.itemView)
                    .load(AppConfig.BASE_URL + info.getPostImageUrl())
                    .into(holder.postImage);
        }

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
                    evaluateList.set(position, info);
                    EvaluateBrowserAdapter.this.notifyDataSetChanged();
                    // 更新服务端
                    viewModel.giveALike(LikeReqDto.of(info.getPostId(), AppConfig.LIKE_TYPE_POST));
                }else {
                    info.setLikeNumber(info.getLikeNumber() - 1);
                    info.setLikeStatus(!info.isLikeStatus());
                    evaluateList.set(position, info);
                    EvaluateBrowserAdapter.this.notifyDataSetChanged();
                    viewModel.giveALike(LikeReqDto.of(info.getPostId(), AppConfig.LIKE_TYPE_POST));
                }
            }
        });





    }

    @Override
    public int getItemCount() {
        return evaluateList != null ? evaluateList.size() : 0;
    }





    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView userAvatar;
        TextView userName;
        TextView postTime;
        TextView postHeadline;
        ImageView postImage;
        ShineButton buttonLike;
        TextView likeNumber;
        TextView commentNumber;
        TextView storeNameDish;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userAvatar = itemView.findViewById(R.id.userAvatar);
            userName = itemView.findViewById(R.id.userName);
            postTime = itemView.findViewById(R.id.postTime);
            postHeadline = itemView.findViewById(R.id.postHeadline);
            postImage = itemView.findViewById(R.id.postImage);
            buttonLike = itemView.findViewById(R.id.buttonLike);
            likeNumber = itemView.findViewById(R.id.likeNumber);
            commentNumber = itemView.findViewById(R.id.commentNumber);
            storeNameDish = itemView.findViewById(R.id.storeNameDish);
        }
    }
}
