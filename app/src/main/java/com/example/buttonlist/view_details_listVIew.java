package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buttonlist.model.User;

import java.util.ArrayList;
import java.util.List;

public class view_details_listVIew extends AppCompatActivity {
   ListView user_listview;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details_list_view);


        tv = findViewById(R.id.displaytext);
        user_listview = findViewById(R.id.userinfo);

        Intent intent = getIntent();

        final List<User> detailuserlist = (List<User>)intent.getSerializableExtra("userlist");
        String [] userNames=new String[detailuserlist.size()];

        int i= 0;
        for (User user:detailuserlist){
            userNames[i]= user.getName();
            i++;
        }



        ArrayAdapter<String> adapterlistview = new ArrayAdapter<String>(this, R.layout.listview_values, userNames);
        user_listview.setAdapter(adapterlistview);

        user_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(view_details_listVIew.this, individualDetail.class);
                intent1.putExtra("name",detailuserlist.get(i).getName());
                intent1.putExtra("gender",detailuserlist.get(i).getGender());
                intent1.putExtra("dob",detailuserlist.get(i).getDob());
                intent1.putExtra("country",detailuserlist.get(i).getCountry());
                intent1.putExtra("phone",detailuserlist.get(i).getPhone());
                intent1.putExtra("email",detailuserlist.get(i).getEmail());

                startActivity(intent1);

                //Toast.makeText(view_details_listVIew.this,i, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
