package com.example.egovernment.CardToCard;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.egovernment.DatabaseAccess;
import com.example.egovernment.Message;
import com.example.egovernment.R;

import java.util.concurrent.TimeUnit;

public class CardToCardActivity extends AppCompatActivity {

    Button submit_transfer;
    EditText from_card_edt, to_card_edt, amount_edt, password_edt;
    TextView timer;
    private static final String FORMAT = "%02d:%02d";
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_to_card);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle("Card Transfer");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        submit_transfer = findViewById(R.id.submit_transfer);
        from_card_edt = findViewById(R.id.from_card_transfer);
        to_card_edt = findViewById(R.id.to_card_transfer);
        amount_edt = findViewById(R.id.amount_transfer);
        password_edt = findViewById(R.id.password_transfer);
        timer = findViewById(R.id.timer);

        countDownTimer = new CountDownTimer(19*60000, 1000) {
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

        submit_transfer.setOnClickListener(v -> {
            String from_card = from_card_edt.getText().toString();
            String to_card = to_card_edt.getText().toString();
            String password = password_edt.getText().toString();

            if (!from_card.isEmpty() && !to_card.isEmpty() && !password.isEmpty() && !amount_edt.getText().toString().isEmpty()) {

                int amount = Integer.parseInt(amount_edt.getText().toString());
                DatabaseAccess databaseAccess = new DatabaseAccess(this);

                try {
                    Cursor c1 = databaseAccess.getDb().rawQuery("SELECT phone_number, currency FROM BankAccount WHERE card_number = ? AND password = ? ", new String[]{from_card, password});
                    c1.moveToFirst();
                    String from_phone_number = c1.getString(c1.getColumnIndex("phone_number"));
                    int from_currency = c1.getInt(c1.getColumnIndex("currency"));

                    Cursor c2 = databaseAccess.getDb().rawQuery("SELECT phone_number, currency FROM BankAccount WHERE card_number = ?", new String[]{to_card});
                    c2.moveToFirst();
                    String to_phone_number = c2.getString(c2.getColumnIndex("phone_number"));
                    int to_currency = c2.getInt(c2.getColumnIndex("currency"));

                    Intent intent = new Intent(CardToCardActivity.this, EnsuringActivity.class);

                    if (amount < (from_currency - 10)) {

                        intent.putExtra("from_currency", from_currency);
                        intent.putExtra("from_card", from_card);
                        intent.putExtra("to_currency", to_currency);
                        intent.putExtra("to_card", to_card);
                        intent.putExtra("to_name", to_phone_number);
                        intent.putExtra("amount", amount);

                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        countDownTimer.cancel();
                        finish();

                    } else {
                        Message.message(getApplicationContext(), "You can not transfer this much amount");
                        amount_edt.setText("");
                    }
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
