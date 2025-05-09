package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataAdapter.NewsViewHolder> {

    private List<NewsData> newsList;

    public NewsDataAdapter(List<NewsData> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsData news = newsList.get(position);
        holder.imageView.setImageResource(news.getImage());
        holder.titleTextView.setText(news.getTitle());
        holder.descriptionTextView.setText(news.getDescription());
        holder.dateTextView.setText(news.getDate());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView, descriptionTextView, dateTextView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rvImage);
            titleTextView = itemView.findViewById(R.id.rvName);
            descriptionTextView = itemView.findViewById(R.id.rvDescription);
            dateTextView = itemView.findViewById(R.id.rvDate);
        }
    }
}
