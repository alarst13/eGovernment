package com.example.egovernment.ColorsGame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.egovernment.Message;
import com.example.egovernment.R;

import java.util.Random;

public class LevelOneActivity extends AppCompatActivity {

    ImageButton[] buttons = new ImageButton[9];
    boolean[] b = new boolean[9];
    int point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

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
        for (ImageButton i:buttons) {
            i.setBackgroundColor(color);
        }
        color = Color.rgb(red + 10 , green + 10 , blue + 10);
        int g = random.nextInt(9);
        b[g] = true;
        buttons[g].setBackgroundColor(color);
    }

    public void game(Button button) {
        switch (button.getTag().toString()) {
            case "1":
                if (b[1]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "2":
                if (b[2]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "3":
                if (b[3]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "4":
                if (b[4]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "5":
                if (b[5]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "6":
                if (b[6]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "7":
                if (b[7]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "8":
                if (b[8]) {
                    setColor();
                    point++;
                } else {
                    Message.message(getApplicationContext(), "your point : " + point);
                    finish();
                }
                break;
            case "9":
                if (b[9]) {
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
