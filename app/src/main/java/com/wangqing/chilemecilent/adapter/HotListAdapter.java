package com.wangqing.chilemecilent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.object.dto.HotListDto;
import com.wangqing.chilemecilent.utils.StringUtil;

import java.util.List;

public class HotListAdapter extends RecyclerView.Adapter<HotListAdapter.Holder> {


    private List<HotListDto> hotList;

    public void setHotList(List<HotListDto> hotList) {
        this.hotList = hotList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.hot_list_holder, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.no.setText(String.valueOf(position+1));
        holder.headline.setText(StringUtil.omitString(hotList.get(position).getHeadline(), 10));
    }

    @Override
    public int getItemCount() {
        return hotList != null ?hotList.size() : 0;
    }

    static class Holder extends RecyclerView.ViewHolder{
        TextView no;
        TextView headline;
        public Holder(@NonNull View itemView) {
            super(itemView);
            no = itemView.findViewById(R.id.no);
            headline = itemView.findViewById(R.id.headline);
        }
    }
}
