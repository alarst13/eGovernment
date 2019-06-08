package com.example.egovernment.QuizOfKings;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.egovernment.Message;
import com.example.egovernment.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizOfKingsActivity extends AppCompatActivity {

    List<Integer> arr = new ArrayList<>();
    int r = 0;
    int point = 0;
    int i = 0;
    TextView pointView, question;
    Button a1, a2, a3, a4;
    int answer = 0;
    QuizOfKings quizOfKings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_of_kings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle("Quiz Of Kings");


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        question = findViewById(R.id.quiz_of_kings_question);
        a1 = findViewById(R.id.quiz_of_kings_one);
        a2 = findViewById(R.id.quiz_of_kings_two);
        a3 = findViewById(R.id.quiz_of_kings_three);
        a4 = findViewById(R.id.quiz_of_kings_four);
        quizOfKings = new QuizOfKings(this);

        Random random = new Random();
        int LOW = 1;
        int TOP = quizOfKings.questions.size();
        r = random.nextInt(TOP - LOW) + LOW;
        arr.add(r);

        setQuestion();
    }

    public void answer(View view) {
        if (Integer.parseInt(view.getTag().toString()) == answer) {
            point++;
            i++;
            Random random = new Random();
            int LOW = 1;
            int TOP = quizOfKings.questions.size();

            int a = 0;

            while(a != 1) {
                r = random.nextInt(TOP - LOW) + LOW;
                if(!arr.contains(r)){
                    a = 1;
                }
            }

            arr.add(r);
            setQuestion();
        } else {
            i++;
            Random random = new Random();
            int LOW = 1;
            int TOP = quizOfKings.questions.size();

            int a = 0;

            while(a != 1) {
                r = random.nextInt(TOP - LOW) + LOW;
                if(!arr.contains(r)){
                    a = 1;
                }
            }

            arr.add(r);
            setQuestion();
        }
    }

    public boolean setQuestion() {
        if (i < 3) {
            question.setText((i + 1) + ") " + quizOfKings.questions.get(r));
            a1.setText("1) " + quizOfKings.option1.get(r));
            a2.setText("2) " + quizOfKings.option2.get(r));
            a3.setText("3) " + quizOfKings.option3.get(r));
            a4.setText("4) " + quizOfKings.option4.get(r));
            answer = Integer.parseInt(quizOfKings.answer.get(r));
        } else {
            Intent intent = new Intent(QuizOfKingsActivity.this, StartOverOrFinish.class);
            intent.putExtra("point", point);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
        }
        return true;
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
