package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLL,buttonGL,buttonRL,buttonCL,buttonTL,buttonFL,buttonList,buttonRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonLL = findViewById(R.id.btn1);
        buttonCL = findViewById(R.id.btn2);
        buttonGL = findViewById(R.id.btn3);
        buttonRL = findViewById(R.id.btn4);
        buttonTL = findViewById(R.id.btn5);
        buttonFL = findViewById(R.id.btn6);
        buttonList = findViewById(R.id.btn7);
        buttonRecycle = findViewById(R.id.btn8);


        buttonCL.setOnClickListener(this);
        buttonLL.setOnClickListener(this);
        buttonGL.setOnClickListener(this);
        buttonRL.setOnClickListener(this);
        buttonTL.setOnClickListener(this);
        buttonFL.setOnClickListener(this);
        buttonList.setOnClickListener(this);
        buttonRecycle.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.btn7)
        {
            Intent intent = new Intent(this,listView_activity.class);
            startActivity(intent);
        }

        if (view.getId()==R.id.btn6)
        {
            Intent intent = new Intent(this,FL_Activity.class);
            startActivity(intent);
        }

        if (view.getId()==R.id.btn5)
        {
            Intent intent = new Intent(this, TableListActivity.class);
            startActivity(intent);
        }

        if (view.getId()==R.id.btn8)
        {
            Intent intent = new Intent(this, Recycle_View.class);
            startActivity(intent);
        }
        if (view.getId()==R.id.btn3)
        {

        }

        if (view.getId()==R.id.btn2)
        {


        }
        if (view.getId()==R.id.btn1)
        {
            Intent intent = new Intent(this, L_Layout.class);
            startActivity(intent);
        }



    }
}
