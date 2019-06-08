package com.example.egovernment.TextGram;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.egovernment.R;

public class TextGramMActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    Button send;
    TextGramMessageAdapter textGramMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_gram_m);

        listView = findViewById(R.id.text_gram_m_List);
        editText = findViewById(R.id.text_gram_m_edit_text);
        send = findViewById(R.id.text_gram_m_send);

        TextGram.mktextGramMAList();

        textGramMessageAdapter = new TextGramMessageAdapter(this , TextGram.textGramList, TextGram.thisPhone);
        listView.setAdapter(textGramMessageAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle(TextGram.thatPhone);

        send.setOnClickListener(v -> {
            TextGram.mkMessage(getApplicationContext(), TextGram.thatPhone , editText.getText().toString());
            textGramMessageAdapter.notifyDataSetChanged();
            editText.setText("");
        });
    }
}
