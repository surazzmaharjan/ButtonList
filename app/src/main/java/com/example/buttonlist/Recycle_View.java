package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.buttonlist.model.Food;

import java.util.ArrayList;
import java.util.List;



public class Recycle_View extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Food> foods = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle__view);

        recyclerView=findViewById(R.id.rvfoods);

        foods.add(new Food("momo","buff","Rs 500",R.drawable.burger));
        foods.add(new Food("burger","buff","Rs 1200",R.drawable.burger));
        foods.add(new Food("chowmein","chicken","Rs 1500",R.drawable.burger));
        foods.add(new Food("Pesi","cold drinks","Rs 5400",R.drawable.burger));

        MyRvAdapter adapter = new MyRvAdapter(foods);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
