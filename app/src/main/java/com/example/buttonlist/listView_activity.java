package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listView_activity extends AppCompatActivity {
    ListView country_listview;
    String countries[] = {"India", "China", "Australia", "America", "New Zealand"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_activity);

        country_listview = findViewById(R.id.countrylist);
        ArrayAdapter<String> adapterlistview = new ArrayAdapter<String>(this, R.layout.listview_values, countries);
        country_listview.setAdapter(adapterlistview);
    }
}
