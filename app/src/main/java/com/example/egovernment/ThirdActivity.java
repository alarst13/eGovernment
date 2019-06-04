package com.example.egovernment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.egovernment.ColorsGame.ColorsGameActivity;
import com.example.egovernment.Dictionary.DictionaryActivity;
import com.example.egovernment.Library.LibraryActivity;
import com.example.egovernment.News.NewsActivity;
import com.example.egovernment.QuizOfKings.QuizOfKingsActivity;

public class ThirdActivity extends AppCompatActivity {

    ImageButton colorsGame, library, news , quizOfKing , dictionary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle(PreferenceData.getLoggedInPhoneNumberUser(this));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }
        colorsGame = findViewById(R.id.main_colors_game);
        colorsGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this , ColorsGameActivity.class));
            }
        });

        news = findViewById(R.id.main_news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this , NewsActivity.class));
            }
        });

        library = findViewById(R.id.fab1);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this, LibraryActivity.class));
            }
        });

        quizOfKing = findViewById(R.id.main_quiz);
        quizOfKing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this , QuizOfKingsActivity.class));
            }
        });

        dictionary = findViewById(R.id.main_dictionary);
        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this , DictionaryActivity.class));
            }
        });
    }
}
