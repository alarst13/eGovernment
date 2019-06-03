package com.example.egovernment.ColorsGame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.egovernment.Message;
import com.example.egovernment.R;

import java.util.Random;

public class LevelOneActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];
    Button start;
    TextView pointView;
    boolean[] b = new boolean[9];
    int point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        buttons[0] = findViewById(R.id.color_game_one_1);
        buttons[1] = findViewById(R.id.color_game_one_2);
        buttons[2] = findViewById(R.id.color_game_one_3);
        buttons[3] = findViewById(R.id.color_game_one_4);
        buttons[4] = findViewById(R.id.color_game_one_5);
        buttons[5] = findViewById(R.id.color_game_one_6);
        buttons[6] = findViewById(R.id.color_game_one_7);
        buttons[7] = findViewById(R.id.color_game_one_8);
        buttons[8] = findViewById(R.id.color_game_one_9);
        start = findViewById(R.id.color_game_one_start);
        pointView = findViewById(R.id.color_game_one_point);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = 0;
                setColor();
            }
        });
    }

    public void setColor() {
        for (int i = 0; i < b.length; i++) {
            b[i] = false;
        }
        int red,blue,green;
        Random random = new Random();
        red = random.nextInt(200);
        blue = random.nextInt(200);
        green = random.nextInt(200);
        int color = Color.rgb(red , green , blue);
        for (Button i:buttons) {
            i.setBackgroundColor(color);
        }
        color = Color.rgb(red + 20, green + 20 , blue + 20);
        int g = random.nextInt(9);
        b[g] = true;
        buttons[g].setBackgroundColor(color);
        pointView.setText("your point : " + point);
    }

    public void game(View view) {
        switch (view.getTag().toString()) {
            case "1":
                if (b[0]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "2":
                if (b[1]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "3":
                if (b[2]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "4":
                if (b[3]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "5":
                if (b[4]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "6":
                if (b[5]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "7":
                if (b[6]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "8":
                if (b[7]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "9":
                if (b[8]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
        }
    }
}
