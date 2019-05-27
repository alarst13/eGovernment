package com.example.egovernment.News;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.egovernment.R;

import java.util.LinkedList;

public class NewsActivity extends AppCompatActivity {

    LinkedList<News> news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        NewsLinkList newsLinkList = (NewsLinkList) getIntent().getSerializableExtra("news");
        news = newsLinkList.news;
        RecyclerView  recyclerView = findViewById(R.id.news_list);
        NewsAdapter newsAdapter = new NewsAdapter(news);
        recyclerView.setAdapter(newsAdapter);
    }
}
