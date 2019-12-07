package com.example.buttonlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImplicitActivity extends AppCompatActivity {
    Button buttonCamera , buttonWebsite, buttonGallery, buttonDailer;
    EditText editdailer;
    ImageView imageView;
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        buttonCamera = findViewById(R.id.btnopencamera);
        buttonGallery = findViewById(R.id.btnopengallery);
        buttonWebsite = findViewById(R.id.btnopenwebsite);
        buttonDailer = findViewById(R.id.btnopendailer);

        editdailer = findViewById(R.id.numberdailer);
        imageView = findViewById(R.id.galleryimage);

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.btnopencamera){

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,1);


                }
            }
        });


        buttonWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.btnopenwebsite){
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com"));
                    startActivity(intent2);

                }
            }
        });

        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.btnopengallery){
                    Intent intent3 = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent3,2);


                }
            }
        });

        buttonDailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.btnopendailer){

                    Uri number= Uri.parse("tel:" +editdailer.getText().toString());
                    Intent intent4 = new Intent(Intent.ACTION_DIAL,number);
                    startActivity(intent4);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            image = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(image);
            askPermission();
        }


        if(requestCode==2){
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                saveCapImg();
            }else{
                Toast.makeText(this, "No permission", Toast.LENGTH_SHORT).show();
            }
        }
    }




    public void askPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            saveCapImg();
        }

    }



    private void saveCapImg(){
        try{
            File mydirector = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Myapp");
            mydirector.mkdir();
            Random random = new Random();
            int a = random.nextInt(1000);
            File file = new File(mydirector,"myimg"+a+".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG,100,fos);
            Toast.makeText(this, "Saved to"+mydirector, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }





}
