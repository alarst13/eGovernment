package com.example.egovernment.CardToCard;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.egovernment.DatabaseAccess;
import com.example.egovernment.R;
import com.example.egovernment.ThirdActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EnsuringActivity extends AppCompatActivity {

    Button btn_sure, btn_not_sure;
    TextView are_you_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ensuring);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle("Card Transfer");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        btn_sure = findViewById(R.id.sure_btn);
        btn_not_sure = findViewById(R.id.not_sure_btn);
        are_you_sure = findViewById(R.id.are_you_sure_txt);

        DatabaseAccess databaseAccess = new DatabaseAccess(this);
        ContentValues contentValues1 = new ContentValues();
        ContentValues contentValues2 = new ContentValues();

        int amount = getIntent().getIntExtra("amount", 0);
        int from_currency = getIntent().getIntExtra("from_currency", 0);
        int to_currency = getIntent().getIntExtra("to_currency", 0);
        String from_card = getIntent().getStringExtra("from_card");
        String to_card = getIntent().getStringExtra("to_card");
        String to_name = getIntent().getStringExtra("to_name");

        contentValues1.put("currency", from_currency - amount);
        contentValues2.put("currency", to_currency + amount);

        are_you_sure.setText("Are you %100 sure \n that you're gonna transfer \n" + amount +  " much money to " + to_name + "?");

        btn_sure.setOnClickListener(v -> {
            databaseAccess.getDb().update("BankAccount", contentValues2, "card_number = ?", new String[]{to_card});
            databaseAccess.getDb().update("BankAccount", contentValues1, "card_number = ?", new String[]{from_card});

            String current_date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());

            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("card_number", from_card);
            contentValues3.put("date", current_date);
            contentValues3.put("type", "Withdrawal");
            contentValues3.put("amount", amount);
            contentValues3.put("bank_balance", from_currency - amount);
            databaseAccess.getDb().insert("AccountTurnover", null, contentValues3);

            ContentValues contentValues4 = new ContentValues();
            contentValues4.put("card_number", to_card);
            contentValues4.put("date", current_date);
            contentValues4.put("type", "Deposit");
            contentValues4.put("amount", amount);
            contentValues4.put("bank_balance", to_currency + amount);
            databaseAccess.getDb().insert("AccountTurnover", null, contentValues4);

            ContentValues contentValues5 = new ContentValues();
            contentValues5.put("card_number", to_card);
            contentValues5.put("date", "2019/06/07");
            contentValues5.put("type", "Deposit");
            contentValues5.put("amount", amount);
            contentValues5.put("bank_balance", to_currency + amount);
            databaseAccess.getDb().insert("AccountTurnover", null, contentValues5);

            ContentValues contentValues6 = new ContentValues();
            contentValues6.put("card_number", to_card);
            contentValues6.put("date", "2019/07/05");
            contentValues6.put("type", "Deposit");
            contentValues6.put("amount", amount);
            contentValues6.put("bank_balance", to_currency + amount);
            databaseAccess.getDb().insert("AccountTurnover", null, contentValues6);

            Intent intent = new Intent(EnsuringActivity.this, GoToTheMainPageActivity.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
        btn_not_sure.setOnClickListener(v -> finish());
    }
}
