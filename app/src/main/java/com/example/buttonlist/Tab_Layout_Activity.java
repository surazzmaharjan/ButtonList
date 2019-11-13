package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Tab_Layout_Activity extends AppCompatActivity {

    TabLayout tablayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab__layout_);


        tablayout = findViewById(R.id.mytab);
        viewPager = findViewById(R.id.mypager);


        FragmentManager fm = getSupportFragmentManager();
        MyVpAdapter adapter = new MyVpAdapter(fm);
        viewPager.setAdapter(adapter);
    }
}
