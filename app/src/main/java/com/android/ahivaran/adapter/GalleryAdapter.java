package com.android.ahivaran.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.ahivaran.R;
import com.android.ahivaran.domain.Photo;
import com.android.ahivaran.utils.RandomColor;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryHolder> {

    private List<Photo> photoList;
    private Context context;

    public GalleryAdapter(Context context) {
        this.photoList = new ArrayList<>();
        this.context = context;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList.addAll(photoList);
        notifyDataSetChanged();
    }

    @Override
    public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GalleryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(GalleryHolder holder, int position) {
        Photo photo = photoList.get(position);
        Glide.with(context)
                .load(context.getString(R.string.image_url, photo.getServer(), photo.getId(), photo.getSecret()))
                .fitCenter()
                .placeholder(RandomColor.getRandomColor())
                .into(holder.itemImv);
    }

    @Override
    public int getItemCount() {
        return photoList != null ? photoList.size() : 0;
    }

    public static class GalleryHolder extends RecyclerView.ViewHolder {

        private ImageView itemImv;
        public GalleryHolder(View itemView) {
            super(itemView);
            itemImv = (ImageView) itemView.findViewById(R.id.itemImv);
        }
    }
}
