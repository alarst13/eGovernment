package com.example.egovernment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.egovernment.AccountTurnover.AccountTurnoverEntry;
import com.example.egovernment.CardToCard.CardToCardActivity;
import com.example.egovernment.ColorsGame.ColorsGameActivity;
import com.example.egovernment.Dictionary.DictionaryActivity;
import com.example.egovernment.Library.LibraryActivity;
import com.example.egovernment.News.NewsActivity;
import com.example.egovernment.PDFConverter.SelectingImage;
import com.example.egovernment.QuizOfKings.QuizOfKingsActivity;
import com.example.egovernment.TextGram.TextGram;
import com.example.egovernment.TextGram.TextGramActivity;

public class ThirdActivity extends AppCompatActivity {

    ImageButton colorsGame, library, news, card_to_card, age_detection, quizOfKing, account_turnover, pdf_converter, telegram , accelerometer ,dictionary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
        card_to_card = findViewById(R.id.fab2);
        card_to_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this, CardToCardActivity.class));
            }
        });
        age_detection = findViewById(R.id.fab7);
        age_detection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this, AgeDetectionActivity.class));
            }
        });
        account_turnover = findViewById(R.id.fab3);
        account_turnover.setOnClickListener(v -> {
            startActivity(new Intent(ThirdActivity.this, AccountTurnoverEntry.class));
        });
        pdf_converter = findViewById(R.id.fab9);
        pdf_converter.setOnClickListener(v -> {
            startActivity(new Intent(ThirdActivity.this, SelectingImage.class));
        });

        telegram = findViewById(R.id.main_telegram);
        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextGram.thisPhone = "09129567534";
                startActivity(new Intent(ThirdActivity.this , TextGramActivity.class));
            }
        });

        accelerometer = findViewById(R.id.fab12);
        accelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(ThirdActivity.this, AccelerometerActivity.class));
                }catch (Exception e){
                    Message.message(getApplicationContext() , e.getMessage());
                }
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
