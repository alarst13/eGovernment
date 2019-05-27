package com.example.egovernment.ColorsGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.egovernment.R;

public class ColorsGameActivity extends AppCompatActivity {

    Button levelOne ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors_game);
        levelOne = findViewById(R.id.level_one);

        levelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColorsGameActivity.this , LevelOneActivity.class));
            }
        });
    }
}
