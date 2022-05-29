package com.example.captureimagedemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
   ImageView imageView;
   Button btOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assign variable
        imageView = findViewById(R.id.image_view);
        btOpen = findViewById(R.id.bt_open);
        //Request for camera permission
      if (ContextCompat.checkSelfPermission(MainActivity.this,
              Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
          ActivityCompat.requestPermissions(MainActivity.this,
                  new String[]{
                          Manifest.permission.CAMERA
                  },
                  100);
      }
      btOpen.setOnClickListener(new View.OnClickListener() {
          @SuppressWarnings("deprecation")
          @Override
          public void onClick(View view) {
              //open camera
             Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             startActivityForResult(intent,100);
          }
      });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //get capture image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            //set capture image to imageview
            imageView.setImageBitmap(captureImage);

        }
    }
}