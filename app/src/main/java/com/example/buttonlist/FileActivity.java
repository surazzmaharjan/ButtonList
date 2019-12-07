package com.example.buttonlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileActivity extends AppCompatActivity {

    EditText editText;
    Button buttonSave , buttonLoad,buttonSaveExternal,buttonLoadExternal;
    HashMap myhm = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        editText = findViewById(R.id.filename);
        buttonSave = findViewById(R.id.btnsave);
        buttonLoad= findViewById(R.id.btnload);
        buttonLoadExternal= findViewById(R.id.btnloadexternal);
        buttonSaveExternal= findViewById(R.id.btnsaveexternal);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = editText.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput("myfile.txt",MODE_PRIVATE);
                    fos.write(data.getBytes());
                editText.getText().clear();
                    Toast.makeText(FileActivity.this, "Saved to"+getFilesDir(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.d("Exc",e.toString());
//                    e.printStackTrace();
                }

            }
        });


        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = openFileInput("myfile.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String data;

                    while ((data=br.readLine()) !=null){
                        String[] wm =data.split("=");
                        myhm.put(wm[0],wm[1]);
                        sb.append(data+"\n");
                    }

                    editText.setText(sb.toString());
                } catch (Exception e) {
                    Log.d("Exc",e.toString());
//                    e.printStackTrace();

                }
            }
        });


        buttonSaveExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExternal();
            }
        });

        if(isExWritable()){
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                saveExternal();
            }else{
                Toast.makeText(this, "No permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isExWritable(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            return true;
        }
        else{
            return false;
        }
    }


    public void askPermission(View view){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            saveExternal();
        }

    }


    private void saveExternal(){
        if(isExWritable()){
            String data =editText.getText().toString();
            try{

                File mydir = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"Myapplic");
                mydir.mkdir();
                File myfile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"Myapplic/myfile.txt");

                myfile.createNewFile();

                FileOutputStream fos = new FileOutputStream(myfile);
                fos.write(data.getBytes());
                editText.getText().clear();
                Toast.makeText(this, "Save To"+myfile, Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Log.d("my",e.toString());
//                e.printStackTrace();

            }
        }
    }
}
