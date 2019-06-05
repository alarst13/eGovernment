package com.example.egovernment.AccountTurnover;

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
import android.widget.EditText;

import com.example.egovernment.Message;
import com.example.egovernment.R;

import org.threeten.bp.LocalDateTime;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DefiningDateRange extends AppCompatActivity {

    Button submit_date_range;
    MyEditTextDatePicker myEditTextDatePicker1,myEditTextDatePicker2;
    EditText from_edt, to_edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defining_date_range);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle("Date Range");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        String card_number = getIntent().getStringExtra("card_num");
        myEditTextDatePicker1 = new MyEditTextDatePicker(this, R.id.from_date_edt);
        myEditTextDatePicker2 = new MyEditTextDatePicker(this, R.id.to_date_edt);

        submit_date_range = findViewById(R.id.submit_date_range);
        from_edt = findViewById(R.id.from_date_edt);
        to_edt = findViewById(R.id.to_date_edt);

        submit_date_range.setOnClickListener(v -> {
            String from_date = from_edt.getText().toString();
            String to_date = to_edt.getText().toString();


            if(!from_date.isEmpty() && !to_date.isEmpty()){
                if(from_date.compareTo(to_date) <= 0){
                    Intent intent = new Intent(DefiningDateRange.this, AccountTurnover.class);
                    intent.putExtra("card_num", card_number);
                    intent.putExtra("from_date", from_date);
                    intent.putExtra("to_date", to_date);
                    startActivity(intent);
                }
                else{
                    Message.message(getApplicationContext(), "Your earlier date is surprisingly later than your later date ... Please try again");
                }
            }
            else{
                Message.message(getApplicationContext(), "Please fill all the fields");
            }

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
