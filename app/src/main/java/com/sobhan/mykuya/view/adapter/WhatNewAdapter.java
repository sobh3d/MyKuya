package com.sobhan.mykuya.view.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sobhan.mykuya.R;
import com.sobhan.mykuya.model.Product;
import com.sobhan.mykuya.model.WhatNew;

import java.util.Collections;
import java.util.List;

public class WhatNewAdapter extends RecyclerView.Adapter<WhatNewAdapter.MyViewHolder> {
    private WhatNew[] whatNews;
    private Context context;

    public WhatNewAdapter(Context context, WhatNew[] whatNews) {
        this.whatNews = whatNews;
        this.context = context;

    }

    @NonNull
    @Override
    public WhatNewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.what_new_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatNewAdapter.MyViewHolder holder, int position) {

        final WhatNew news = whatNews[position];
        holder.title.setText(whatNews[position].getTitle());
        holder.des.setText(whatNews[position].getDes());
        Glide.with(context)
                .load(Uri.parse(whatNews[position].getImage()))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.image.setBackground(resource);
                    }
                });


    }

    @Override
    public int getItemCount() {
        return whatNews.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, des;
        ImageView image;


        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_what_new_title);
            image = view.findViewById(R.id.iv_what_new_item);
            des = view.findViewById(R.id.tv_what_new_desc);

        }
    }


}

