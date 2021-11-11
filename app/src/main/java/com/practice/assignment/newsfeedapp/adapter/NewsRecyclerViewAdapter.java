package com.practice.assignment.newsfeedapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.assignment.newsfeedapp.R;
import com.practice.assignment.newsfeedapp.databinding.ItemNewsListBinding;
import com.practice.assignment.newsfeedapp.model.NewsFeedModel;
import com.practice.assignment.newsfeedapp.model.network.NewsArticle;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    private List<NewsArticle> newsFeedList;
    private Context context;

    public NewsRecyclerViewAdapter(List<NewsArticle> newsFeedList, Context context) {
        this.newsFeedList = newsFeedList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNewsListBinding binding = ItemNewsListBinding.inflate(inflater, parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsArticle model = newsFeedList.get(position);
        holder.binding.setNews(model);
        File file = null;
        try {
            if (model.getUrlToImage() != null && !model.getUrlToImage().isEmpty())
                file = new File(URI.create(model.getUrlToImage()));
            else
                file = new File(String.valueOf(R.drawable.placeholder));

            Picasso.with(context)
                    .load(file)
                    .rotate(90f)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.binding.ivNewsImage);
                /*Picasso.with(context)
                        .load(model.getUrlToImage())
                        .rotate(90f)
                        .error(R.drawable.placeholder)
                        .placeholder(R.drawable.placeholder)
                        .into(holder.binding.ivNewsImage);*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return newsFeedList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        ItemNewsListBinding binding;

        public NewsViewHolder(@NonNull ItemNewsListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public void removeTopItem() {
        newsFeedList.remove(0);
        notifyDataSetChanged();
    }
}
