package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity {

    Button first,second;
    Boolean check= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        first = findViewById(R.id.btnfirst);
//        second = findViewById(R.id.btnsecond);

        first.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(check) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragHolder, new BasicFragment());
                    fragmentTransaction.commit();
                    check =  false;
                    first.setText("Load Second");
                }
                else{
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragHolder, new SecondFragment());
                    fragmentTransaction.commit();
                    check =  true;
                    first.setText("Load First");
                }

            }

        });


//        second.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fragmentManager1 = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
//                fragmentTransaction1.replace(R.id.fragHolder,new SecondFragment());
//                fragmentTransaction1.commit();
//            }
//        });
    }
}
