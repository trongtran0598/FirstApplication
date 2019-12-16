package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MainActivityCountry extends AppCompatActivity {

    ListView listViewCountry;
    ArrayList<String> list, listData;
    Intent intent, intentData;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_country);

        listViewCountry = findViewById(R.id.listCtr);
        toolbar = findViewById(R.id.toolbarCountry);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = new Intent(this, MainActivityRegister.class);
        intentData = getIntent();
        listData = intentData.getStringArrayListExtra("listData");

        khoiTao();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivityCountry.this,
                android.R.layout.simple_list_item_1, list);
        listViewCountry.setAdapter(adapter);

        listViewCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("Country", list.get(i));
                intent.putStringArrayListExtra("listData", listData);
                startActivity(intent);
                finish();

            }
        });
    }

    private void khoiTao() {
        list = new ArrayList<>();
        list.add("Việt Nam");
        list.add("Anh");
        list.add("Pháp");
        list.add("Mỹ");
        list.add("Nhật");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

