package com.sobhan.mykuya.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.sobhan.mykuya.R;
import com.sobhan.mykuya.model.Product;
import com.sobhan.mykuya.model.Result;
import com.sobhan.mykuya.view.MainActivity;


import java.net.URI;
import java.util.List;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.MyViewHolder> {
    private List<Product> results;
    private Context context;

    public AllAdapter(Context context, List<Product> results) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Product product = results.get(position);

        holder.title.setText(product.getCommercialName());
        Uri uri = Uri.parse(product.getIcon());
        Glide.with(context)
                .load(uri)
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;


        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_all_service);
            image = view.findViewById(R.id.iv_all_service_item);

        }
    }
}

