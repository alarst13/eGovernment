package com.example.egovernment.Library;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.example.egovernment.DatabaseAccess;
import com.example.egovernment.DatabaseHelper;
import com.example.egovernment.R;
import com.example.egovernment.model.User;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {
    private static final String TAG = "LibraryActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        Log.d(TAG, "onCreate: started.");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle("Library");


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        initImageBitmaps();
    }
    private void initImageBitmaps(){

        DatabaseAccess databaseAccess = new DatabaseAccess(getApplicationContext());
        List<PDF> pdfs = new ArrayList<>();
        Cursor c = databaseAccess.getDb().rawQuery("SELECT * FROM PDF",null);
        c.moveToFirst();
        int i =0;
        while(!c.isAfterLast()){
            String pdf_name = c.getString(0);
            String image_url = c.getString(1);
            String pdf_url = c.getString(2);
            PDF pdf = new PDF(pdf_name,image_url,pdf_url);
            pdfs.add(pdf);
            c.moveToNext();
            i++;
        }
        System.out.println(i);
        for(PDF pdf : pdfs) {
            mImageUrls.add(pdf.getImage_url());
            mNames.add(pdf.getPdf_name());
        }

        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        LibraryRecyclerViewAdapter adapter = new LibraryRecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
