package com.example.egovernment.QuizOfKings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.egovernment.Message;
import com.example.egovernment.R;

public class QuizOfKingsActivity extends AppCompatActivity {

    int point = 0;
    TextView pointView , question;
    Button a1,a2,a3,a4;
    int answer = 0;
    QuizOfKings quizOfKings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_of_kings);

        question = findViewById(R.id.quiz_of_kings_question);
        a1 = findViewById(R.id.quiz_of_kings_one);
        a2 = findViewById(R.id.quiz_of_kings_two);
        a3 = findViewById(R.id.quiz_of_kings_three);
        a4 = findViewById(R.id.quiz_of_kings_four);
        quizOfKings = new QuizOfKings();

        setQuestion();
    }
    public void answer(View view){
        if (Integer.parseInt(view.getTag().toString()) == answer){
            point++;
            setQuestion();
        }
        else {
            Message.message(getApplicationContext() , "your point : " + point);
            finish();
        }
    }
    public boolean setQuestion(){
        if (point < quizOfKings.questions.size()){
            question.setText((point + 1) + ") " + quizOfKings.questions.get(point));
            a1.setText("1)" + quizOfKings.answer1.get(point));
            a2.setText("2)" + quizOfKings.answer2.get(point));
            a3.setText("3)" + quizOfKings.answer3.get(point));
            a4.setText("4)" + quizOfKings.answer4.get(point));
            answer = Integer.parseInt(quizOfKings.answer.get(point));
        }else {
            Message.message(getApplicationContext() , "your are win");
            finish();
        }
        return true;
    }
}
