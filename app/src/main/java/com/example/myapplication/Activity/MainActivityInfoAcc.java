package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityInfoAcc extends AppCompatActivity {

    CircleImageView image;
    TextView name, changePass, credit, logout, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info_acc);

        setView();
    }

    private void setView() {
        phoneNumber = findViewById(R.id.phoneNumber);
        image = findViewById(R.id.circleImageView2);
        name = findViewById(R.id.nameAccount);
        changePass = findViewById(R.id.changePass);
        credit = findViewById(R.id.credit);
        logout = findViewById(R.id.logout);

    }
}
