package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class SharedPreActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pre);

        sp = getApplicationContext().getSharedPreferences("mysp",MODE_PRIVATE);
        editor = sp.edit();

//        editor.putString("nam","Mr.Ssss");
//        editor.putInt("phone",12345689);
//        editor.putBoolean("isMember",true);
//        editor.commit();
//

        String n = sp.getString("name","hello one");
        int a = sp.getInt("age",0);
        boolean im = sp.getBoolean("isMember",false);
        int b = sp.getInt("zip",0);
        String test = sp.getString("address","Nepal");

        Toast.makeText(this, "Name: "+n +"\n"+"Age: "+a+"\n"+"Member: "+im+"\n"+"ZIP: "+b+"\n"+"Address: "+test, Toast.LENGTH_SHORT).show();
    }
}
