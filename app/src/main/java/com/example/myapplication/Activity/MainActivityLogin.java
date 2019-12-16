package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivityLogin extends AppCompatActivity {
    Button dangNhap, dangKy;
    Toolbar toolbar;
    EditText txtEmail, txtPass;
    TextView forgotPass;
    ProgressBar progressBar;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        setView();

        auth = FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Đăng nhập");

        final Intent intent = new Intent(this, MainActivityRegister.class);

        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });


        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPass.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(MainActivityLogin.this, "Nhập địa chỉ email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()){
                    Toast.makeText(MainActivityLogin.this, "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivityLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivityLogin.this, "Địa chỉ email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }else{
                            if(auth.getCurrentUser().isEmailVerified()){
                                startActivity(new Intent(MainActivityLogin.this, MainActivityAccount.class));
                            }else {
                                Toast.makeText(MainActivityLogin.this, "Vui lòng xác thực địa chỉ email của bạn", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityLogin.this, MainActivityForgotPass.class));
            }
        });

    }

    private void setView() {
        dangKy = findViewById(R.id.dangky);
        dangNhap = findViewById(R.id.dangnhap);
        toolbar = findViewById(R.id.toolbar_login);
        progressBar = findViewById(R.id.progressBar);
        txtEmail = findViewById(R.id.email);
        txtPass = findViewById(R.id.password);
        forgotPass = findViewById(R.id.forgot_pass);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

