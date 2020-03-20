package com.wangqing.chilemecilent.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageReplyAdapter extends RecyclerView.Adapter<MessageReplyAdapter.Holder> {



    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }




    static class Holder extends RecyclerView.ViewHolder{

        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
