package com.example.buttonlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buttonlist.API.EmployeeInterface;
import com.example.buttonlist.model.Flag;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlagApiActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    ImageView imageView;
    Retrofit retrofit;
    EmployeeInterface employeeInterface;
    Button buttonChoose, buttonUpload,btnSave;
    Uri uri;
    String imgPath;
    MultipartBody.Part image;
    String file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_api);
        textView = findViewById(R.id.tvCountry);
        imageView = findViewById(R.id.tvImage);
        editText = findViewById(R.id.etCountry);


        buttonUpload = findViewById(R.id.btnUploadImg);
        btnSave = findViewById(R.id.btnaddCountry);
        buttonChoose = findViewById(R.id.btnaddImg);

        getInstance();
        getCountryByID(20);
//        stuList();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c= editText.getText().toString();
                Toast.makeText(FlagApiActivity.this, file_name, Toast.LENGTH_SHORT).show();
               Flag fg = new Flag(0,c,file_name);
                addCountryByModel(fg);
                 addCountry("hello","hello.png");
            }
        });

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);

            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage(image);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        imageView.setImageURI(uri);
        askPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getImgReady();
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
            getImgReady();
        }

    }

    private void getImgReady(){

        //url ko lagi ko kam
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor= getContentResolver().query(uri,filePathColumn,null,null,null);

        assert cursor !=null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        imgPath = cursor.getString(columnIndex);


        File file = new File(imgPath);
        RequestBody requestBody= RequestBody.create(MediaType.parse("image/*"),file);

        image = MultipartBody.Part.createFormData("file",file.getName(),requestBody);

    }

    private void uploadImage(MultipartBody.Part image){
        Call<Flag> flagUpload = employeeInterface.uploadFlag(image);

        flagUpload.enqueue(new Callback<Flag>() {
            @Override
            public void onResponse(Call<Flag> call, Response<Flag> response) {
                Toast.makeText(FlagApiActivity.this, response.body().getFile()+"Uploaded", Toast.LENGTH_SHORT).show();
                file_name = response.body().getFile();
            }

            @Override
            public void onFailure(Call<Flag> call, Throwable t) {
                Log.d("Ex",t.getMessage());

            }
        });
    }

    private void getInstance(){

//        http://sujitg.com.np/api/
//        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:4000/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        retrofit = new Retrofit.Builder().baseUrl("http://sujitg.com.np/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        employeeInterface = retrofit.create(EmployeeInterface.class);
    }

    private void getCountryByID(int id) {
        Call<Flag> flagCall= employeeInterface.getFlagById(id);

        flagCall.enqueue(new Callback<Flag>() {
            @Override
            public void onResponse(Call<Flag> call, Response<Flag> response) {


                textView.setText(response.body().getCountry());
                Picasso.with(FlagApiActivity.this)
                        .load("http://sujitg.com.np/wc/teams/"+response.body().getFile())
                        .into(imageView);
//                "-->"+response.body().getFile()));

            }

            @Override
            public void onFailure(Call<Flag> call, Throwable t) {
                Log.d("Ex",t.getMessage());
            }
        });
    }

     private void addCountry(String country,String file){

        Call<Void> addC = employeeInterface.addCountry(country,file);

        addC.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(FlagApiActivity.this, "Added", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Ex",t.getMessage());

            }
        });
    }

    private void addCountryByModel(Flag flag){

        Call<Void> addC = employeeInterface.addCountryByModel(flag);

        addC.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(FlagApiActivity.this, "Added by Model", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Ex",t.getMessage());

            }
        });
    }


    private void stuList(){
        Call<List<String>> studList = employeeInterface.getNodeStudents();

        studList.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Toast.makeText(FlagApiActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("Ex",t.getMessage());

            }
        });


    }

    }






