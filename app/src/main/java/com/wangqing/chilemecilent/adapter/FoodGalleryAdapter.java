package com.wangqing.chilemecilent.adapter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wangqing.chilemecilent.R;
import com.wangqing.chilemecilent.object.dto.FoodGalleryDto;
import com.wangqing.chilemecilent.utils.AppConfig;

import java.util.ArrayList;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class FoodGalleryAdapter extends RecyclerView.Adapter<FoodGalleryAdapter.PhotoHolder> {

    private List<FoodGalleryDto> photoItemList = new ArrayList<>() ;

    public void setPhotoItemList(List<FoodGalleryDto> photoItemList) {
        this.photoItemList = photoItemList;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.food_gallery_holder, parent, false);
        return new PhotoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        holder.shimmerLayout.setShimmerColor(0x55FFFFFF);
        holder.shimmerLayout.setShimmerAngle(0);
        holder.shimmerLayout.startShimmerAnimation();

        Glide.with(holder.itemView)
                .load(AppConfig.BASE_URL + photoItemList.get(position).getImageUrl())
                .placeholder(R.drawable.ic_photo_gray_24dp)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (holder != null){
                            holder.shimmerLayout.stopShimmerAnimation();
                        }
                        return false;
                    }
                })
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("PHOTO_LIST", (ArrayList<? extends Parcelable>) photoItemList);
//                bundle.putInt("PHOTO_CURRENT_POSITION", position);
//                NavController controller = Navigation.findNavController(v);
//                controller.navigate(R.id.action_galleryFragment_to_pagerPhotoFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoItemList.size();
    }


    static class PhotoHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ShimmerLayout shimmerLayout;
        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
            shimmerLayout = itemView.findViewById(R.id.shimmerLayout);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
