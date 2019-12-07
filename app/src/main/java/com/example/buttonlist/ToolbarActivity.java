package com.example.buttonlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.menuOuter){
            Toast.makeText(this, "Menu outer", Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()== R.id.menuFirst){
            Toast.makeText(this, "Menu First", Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()== R.id.menuSecond){
            Toast.makeText(this, "Menu Second", Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()== R.id.sub1){
            Toast.makeText(this, "Menu Second Sub 1", Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()== R.id.sub2){
            Toast.makeText(this, "Menu Second Sub 2", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
