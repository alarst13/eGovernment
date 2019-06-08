package com.example.egovernment.QuizOfKings;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.egovernment.R;

public class StartOverOrFinish extends AppCompatActivity {

    Button start_over, exit;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_over_or_finish);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle("Quiz Of Kings");


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        int point = getIntent().getIntExtra("point", 0);
        result = findViewById(R.id.result);
        switch (point){
            case 0:
                result.setText("You unfortunately answered all the questions wrongly ...");
                break;
            case 1:
                result.setText("You answered one question correctly ...");
                break;
            case 2:
                result.setText("You answered two questions correctly ...");
                break;
            case 3:
                result.setText("Congrats, you correctly answered all the questions ...");
        }

        start_over = findViewById(R.id.start_over);
        start_over.setOnClickListener(v -> {
            Intent intent = new Intent(StartOverOrFinish.this, QuizOfKingsActivity.class);
            startActivity(intent);
        });
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(v -> {
            finish();
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
