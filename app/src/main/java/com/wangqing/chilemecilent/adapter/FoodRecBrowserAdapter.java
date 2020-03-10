package com.wangqing.chilemecilent.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.object.dto.FRDSelDto;
import com.wangqing.chilemecilent.object.dto.FoodRecBrowserDto;
import com.wangqing.chilemecilent.object.dto.LikeReqDto;
import com.wangqing.chilemecilent.utils.AppConfig;
import com.wangqing.chilemecilent.utils.RelativeDateFormat;
import com.wangqing.chilemecilent.viewmodel.foodRec.FoodRecBrowserViewModel;

import java.util.List;

public class FoodRecBrowserAdapter extends RecyclerView.Adapter<FoodRecBrowserAdapter.FoodRecBrowserViewHolder> {

    private List<FoodRecBrowserDto> foodRecList;
    private Activity activity;
    private FoodRecBrowserViewModel viewModel;

    public FoodRecBrowserAdapter(FragmentActivity activity, FoodRecBrowserViewModel viewModel) {
        super();
        this.activity = activity;
        this.viewModel = viewModel;
    }


    public void setFoodRecList(List<FoodRecBrowserDto> foodRecList) {
        this.foodRecList = foodRecList;
    }


    @NonNull
    @Override
    public FoodRecBrowserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.food_rec_browser_holder, parent, false);
        return new FoodRecBrowserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecBrowserViewHolder holder, int position) {
        FoodRecBrowserDto info = foodRecList.get(position);

        holder.userName.setText(info.getUserName());
        holder.postTime.setText(RelativeDateFormat.format(info.getPostTime()));
        holder.postHeadline.setText(info.getPostHeadline());

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
            holder.likeButton.setChecked(true);
        }else {
            holder.likeButton.setChecked(false);
        }

        holder.likeButton.init(activity);
        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.likeButton.isChecked()){
                    // 更新界面
                    info.setLikeNumber(info.getLikeNumber() + 1);
                    info.setLikeStatus(!info.isLikeStatus());
                    foodRecList.set(position, info);
                    FoodRecBrowserAdapter.this.notifyDataSetChanged();
                    // 更新服务端
                    viewModel.giveALike(LikeReqDto.of(info.getPostId(), AppConfig.LIKE_TYPE_POST));
                }else {
                    info.setLikeNumber(info.getLikeNumber() - 1);
                    info.setLikeStatus(!info.isLikeStatus());
                    foodRecList.set(position, info);
                    FoodRecBrowserAdapter.this.notifyDataSetChanged();
                    viewModel.giveALike(LikeReqDto.of(info.getPostId(), AppConfig.LIKE_TYPE_POST));
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(AppConfig.FOOD_REC_BROWSER_TO_DETAIL_KEY,
                        new FRDSelDto(info.getPostId(), info.getUserId(), viewModel.getAccountManager().getUser().getUserId()));
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_foodRecBrowserFragment_to_foodRecDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {

        return foodRecList != null ? foodRecList.size() : 0;
    }


    static class FoodRecBrowserViewHolder extends RecyclerView.ViewHolder{
        ImageView userAvatar;
        TextView userName;
        TextView postTime;
        TextView postHeadline;
        ImageView postImage;
        ImageView commentImage;
        ShineButton likeButton;
        TextView likeNumber;
        TextView commentNumber;
        public FoodRecBrowserViewHolder(@NonNull View itemView) {
            super(itemView);
            userAvatar = itemView.findViewById(R.id.userAvatar);
            userName = itemView.findViewById(R.id.userName);
            postTime = itemView.findViewById(R.id.postTime);
            postHeadline = itemView.findViewById(R.id.postHeadline);
            postImage = itemView.findViewById(R.id.postImage);
            commentImage = itemView.findViewById(R.id.commentImage);
            likeButton = itemView.findViewById(R.id.buttonLike);
            likeNumber = itemView.findViewById(R.id.likeNumber);
            commentNumber = itemView.findViewById(R.id.commentNumber);

        }
    }



}
