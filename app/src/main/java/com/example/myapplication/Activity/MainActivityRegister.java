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
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivityRegister extends AppCompatActivity {
    Button btnRegister;
    EditText txtMk, txtXnMk, txtEmail;
    Toolbar toolbar;
    ProgressBar progressBar;
    protected FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        auth = FirebaseAuth.getInstance();

        setView();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Đăng ký");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = txtEmail.getText().toString().trim();
                String password = txtMk.getText().toString().trim();
                String password2 = txtXnMk.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(MainActivityRegister.this, "Nhập địa chỉ email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(MainActivityRegister.this, "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(MainActivityRegister.this, "Mật khẩu phải có tối thiểu 6 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(password2)) {
                    Toast.makeText(MainActivityRegister.this, "Xác nhận mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivityRegister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivityRegister.this, "Tài khoản đã được đăng ký hoặc không tồn tại", Toast.LENGTH_SHORT).show();
                        } else {
                            auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivityRegister.this, "Đăng ký thành công, vui lòng kiểm tra email để xác thực", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            });

                        }
                    }
                });


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    private void setView() {
        txtMk = findViewById(R.id.edit_matkhau);
        txtXnMk = findViewById(R.id.edit_xn_matkhau);
        txtEmail = findViewById(R.id.edit_email);
        toolbar = findViewById(R.id.toolbarRegister);
        btnRegister = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
