package com.example.egovernment.TextGram;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.egovernment.R;

import java.util.LinkedList;

public class TextGramActivity extends AppCompatActivity {
    ListView listView;
    TextGramAdapter textGramAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_gram);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle(TextGram.thisPhone);

        TextGram.getFromDB();
        TextGram.mkTextGramActivityList();
        listView = findViewById(R.id.text_gram_list);
        textGramAdapter = new TextGramAdapter(this , TextGram.textGramActivityList , TextGram.thisPhone);
        listView.setAdapter(textGramAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TextGram.thisPhone.equals(TextGram.textGramActivityList.get(position).getSender())){
                    TextGram.thatPhone = TextGram.textGramActivityList.get(position).getReceiver();
                }
                else {
                    TextGram.thatPhone = TextGram.textGramActivityList.get(position).getSender();
                }
                startActivity(new Intent(TextGramActivity.this , TextGramMActivity.class));
                TextGram.mktextGramMAList();
                textGramAdapter.notifyDataSetChanged();
            }
        });

        FloatingActionButton fab = findViewById(R.id.telegram_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });
    }

}
