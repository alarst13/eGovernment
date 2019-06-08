package com.example.egovernment.News;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.egovernment.Message;
import com.example.egovernment.R;
import com.example.egovernment.TextGram.TextGramAdapter;

import java.util.LinkedList;

public class NewsActivity extends AppCompatActivity {

    RecyclerView listView;

    LinkedList<News> news;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listView = findViewById(R.id.news_list);

        news = NewsLinkList.mkShowList(this , NewsLinkList.phoneNumber);
        NewsAdapter newsAdapter = new NewsAdapter ( news );
        listView.setAdapter(newsAdapter);
    }
}
