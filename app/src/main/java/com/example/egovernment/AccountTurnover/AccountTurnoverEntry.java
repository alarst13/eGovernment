package com.example.egovernment.AccountTurnover;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.egovernment.CardToCard.CardToCardActivity;
import com.example.egovernment.CardToCard.EnsuringActivity;
import com.example.egovernment.DatabaseAccess;
import com.example.egovernment.Message;
import com.example.egovernment.R;

import java.util.concurrent.TimeUnit;

public class AccountTurnoverEntry extends AppCompatActivity {

    Button submit;
    EditText card_num, password_edt;
    TextView timer;
    private static final String FORMAT = "%02d:%02d";
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_turnover_entry);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle("Account Turnover");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        submit = findViewById(R.id.submit_turnover);
        card_num = findViewById(R.id.card_num_turnover);
        password_edt = findViewById(R.id.password_turnover);
        timer = findViewById(R.id.timer_turnover);

        countDownTimer = new CountDownTimer(8*60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @Override
            public void onFinish() {
                Message.message(getApplicationContext(), "TIME IS OVER");
                finish();
            }
        }.start();

        submit.setOnClickListener(v -> {
            String card_number = card_num.getText().toString();
            String password = password_edt.getText().toString();

            if (!card_number.isEmpty() && !password.isEmpty()) {

                DatabaseAccess databaseAccess = new DatabaseAccess(this);

                try {
                    Cursor c1 = databaseAccess.getDb().rawQuery("SELECT card_number FROM BankAccount WHERE card_number = ? AND password = ? ", new String[]{card_number, password});
                    c1.moveToFirst();
                    String credit_card_num = c1.getString(c1.getColumnIndex("card_number"));
                    Intent intent = new Intent(AccountTurnoverEntry.this, DefiningDateRange.class);
                    intent.putExtra("card_num", card_number);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    countDownTimer.cancel();
                    finish();
                } catch (Exception e) {
                    Message.message(getApplicationContext(), "The written information does not exist ... please try again");
                }
            } else {
                Message.message(getApplicationContext(), "Please fill all the fields ...");
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                countDownTimer.cancel();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        countDownTimer.cancel();
        finish();
    }
}
