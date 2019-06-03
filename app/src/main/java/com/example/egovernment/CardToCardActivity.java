package com.example.egovernment;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class CardToCardActivity extends AppCompatActivity {

    Button submit_transfer;
    EditText from_card_edt, to_card_edt, amount_edt, password_edt;

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

        String from_card = from_card_edt.getText().toString().trim();
        String to_card = to_card_edt.getText().toString().trim();
        String password = password_edt.getText().toString().trim();
        String amount = amount_edt.getText().toString().trim();

        submit_transfer.setOnClickListener(v -> {
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
