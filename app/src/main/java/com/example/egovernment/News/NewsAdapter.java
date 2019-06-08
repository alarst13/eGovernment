package com.example.egovernment.News;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.egovernment.R;

import java.util.LinkedList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public LinkedList <News> news;

    public NewsAdapter(LinkedList<News> news) {
        this.news = news;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_list_layout, viewGroup , false);

        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        newsViewHolder.title.setText(news.get(i).getTitle());
        newsViewHolder.text.setText(news.get(i).getText());
        //image view
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
    public  static class NewsViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView text;
        public ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_news_list);
            text = itemView.findViewById(R.id.text_news_list);
            imageView = itemView.findViewById(R.id.image_news_list);
        }

    }
}
