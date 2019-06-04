package com.example.egovernment.Dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.egovernment.R;

public class DictionaryActivity extends AppCompatActivity {


    Button search;
    EditText text;
    ListView list;
    TextView textView;

    Dictionary dictionary = new Dictionary();
    DictionaryAdapter dictionaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictinary);

        search = findViewById(R.id.dictionary_search_btn);
        text = findViewById(R.id.dictionary_search_text);
        list = findViewById(R.id.dictionary_list);
        textView = findViewById(R.id.dictionary_text);

        dictionaryAdapter = new DictionaryAdapter(dictionary.wordsForShow , getApplicationContext());
        list.setAdapter(dictionaryAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text.getText().toString();
                if(dictionary.search(string)){
                    textView.setText(null);
                }
                else if (dictionary.wordsForShow.size() == 0){
                    textView.setText("not find any words.");
                }
                else {
                    textView.setText("word look like your search.");
                }
                dictionaryAdapter.notifyDataSetChanged();

            }
        });
    }
}
