package com.example.egovernment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudmersive.client.FaceApi;
import com.cloudmersive.client.RecognizeApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.AgeDetectionResult;
import com.cloudmersive.client.model.ImageDescriptionResponse;
import com.cloudmersive.client.model.ObjectDetectionResult;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class AgeDetectionActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String API_KEY = "e3448870-32d6-4e4b-a7b3-4d561f1c00bf";

    TextView age_txt;
    ImageView take_photo, set_photo;
    Button process;
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private final int REQUEST_CODE = 1;
    private RecognizeApi apiInstance;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_detection);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffb2b2")));
        getSupportActionBar().setTitle("How old do you look?");


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey(API_KEY);

        apiInstance = new RecognizeApi();

        age_txt = findViewById(R.id.age_txt);

        set_photo = findViewById(R.id.set_saved_photo);
        take_photo = findViewById(R.id.take_photo);
        take_photo.setOnClickListener(this);

        process = findViewById(R.id.btn_process);
        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageDetection();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.take_photo) {
            dispatchTakePictureIntent();
            sendImage();
        }
    }

    private void sendImage() {
        try {
            ImageDescriptionResponse response = apiInstance.recognizeDescribe(file);
            Log.d("qaz", response.toString());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE);
        }
    }

    String imagePath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            saveImage(imageBitmap, imagePath);
            set_photo.setImageBitmap(imageBitmap);
        }
    }

    private void saveImage(Bitmap finalBitmap, String image_name) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Image-" + image_name + Calendar.getInstance().getTime() + ".jpg";
        file = new File(myDir, fname);
        imagePath = file.getAbsolutePath();
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ageDetection(){
        new Thread(()->{
            FaceApi apiInstance = new FaceApi();
            File imageFile = new File(imagePath);
            try {
                AgeDetectionResult result = apiInstance.faceDetectAge(imageFile);
                String[] strings = result.getPeopleWithAge().toString().split("\n");
                String[] strings1 = strings[8].split(": ");
                runOnUiThread(()->setText("You look between: " + strings1[1]));
            } catch (ApiException e) {
                System.err.println("Exception when calling FaceApi#faceDetectAge");
                e.printStackTrace();
            }
        }).start();
    }

    private void setText (String b) {
        age_txt.setText(b);
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
