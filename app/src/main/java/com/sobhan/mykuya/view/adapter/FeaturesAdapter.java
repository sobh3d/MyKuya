package com.sobhan.mykuya.view.adapter;

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
import com.sobhan.mykuya.R;
import com.sobhan.mykuya.model.Product;
import com.sobhan.mykuya.model.Result;


import java.util.ArrayList;
import java.util.List;


public class FeaturesAdapter extends RecyclerView.Adapter<FeaturesAdapter.MyViewHolder> {
    private List<Product> results;
    private Context context;

    public FeaturesAdapter(Context context, List<Product> results) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.featured_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Product product = results.get(position);

        holder.title.setText(product.getCommercialName());
        Glide.with(context)
                .load(Uri.parse(product.getIcon()))
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
