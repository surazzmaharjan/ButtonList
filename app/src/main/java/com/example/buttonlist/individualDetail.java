package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class individualDetail extends AppCompatActivity {
    TextView textviewName,textviewGender,textviewdob,textviewcountry,textviewphone,textviewemail;
    ImageView individualUserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_detail);

        individualUserImage= findViewById(R.id.ivUserImg);


        textviewName= findViewById(R.id.displayname);
        textviewGender= findViewById(R.id.displaygender);
        textviewdob= findViewById(R.id.displaydob);
        textviewcountry= findViewById(R.id.displaycountry);
        textviewphone= findViewById(R.id.displayphone);
        textviewemail= findViewById(R.id.displayemail);

        Intent intent = getIntent();
        String name= intent.getStringExtra("name");
        String gender= intent.getStringExtra("gender");
        String dob = intent.getStringExtra("dob");
        String country= intent.getStringExtra("country");
        String phone= intent.getStringExtra("phone");
        String email= intent.getStringExtra("email");

        individualUserImage.setImageResource(R.drawable.user);
        textviewName.setText("Name :"+name);
        textviewGender.setText("Gender :"+gender);
        textviewdob.setText("DOB :"+dob);
        textviewcountry.setText("Country :"+country);
        textviewphone.setText("Phone :"+phone);
        textviewemail.setText("Email :"+email);


    }
}
