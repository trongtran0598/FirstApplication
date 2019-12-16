package com.example.myapplication.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Adapter.FragmentAdapter;
import com.example.myapplication.Fragment.FragmentHandBook;
import com.example.myapplication.Fragment.FragmentNews;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements FragmentNews.SendIntent, FragmentHandBook.SendIntent2 {
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentPagerAdapter fragmentPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.sliding_tabs);

        fragmentPagerAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void sendUrl(String url) {
        Intent i = new Intent(MainActivity.this,MainActivityWeb.class);
//        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        i.putExtra("url",url);
        startActivity(i);
    }
}