package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityInfoAcc extends AppCompatActivity {

    Toolbar toolbar;

    CircleImageView image;
    TextView name, changePass, credit, logout, phoneNumber;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info_acc);

        setView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Trang cá nhân");

        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();


        if (user != null) {
            Picasso.get().load(user.getPhotoUrl()).into(image);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                setResult(RESULT_OK,null);
                finish();
                startActivity(new Intent(MainActivityInfoAcc.this,MainActivityLogin.class));
            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivityInfoAcc.this, user.getDisplayName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setView() {
        toolbar = findViewById(R.id.toolbar_info);
        phoneNumber = findViewById(R.id.phoneNumber);
        image = findViewById(R.id.circleImageView2);
        name = findViewById(R.id.nameAccount);
        changePass = findViewById(R.id.changePass);
        credit = findViewById(R.id.credit);
        logout = findViewById(R.id.logout);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
