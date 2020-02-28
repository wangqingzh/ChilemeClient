package com.wangqing.chilemecilent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sackcentury.shinebuttonlib.ShineButton;
import com.wangqing.chilemecilent.R;

public class FoodRecDetailAdapter extends RecyclerView.Adapter<FoodRecDetailAdapter.ViewHolder> {



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.food_rec_comment_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
