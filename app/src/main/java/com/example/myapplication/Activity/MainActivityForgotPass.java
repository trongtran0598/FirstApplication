package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityForgotPass extends AppCompatActivity {
    Button btnSend;
    EditText txtEmail;
    Toolbar toolbar;
    ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forgot_pass);

        auth = FirebaseAuth.getInstance();
        setView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Quên mật khẩu");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim();

                if (email.isEmpty()){
                    Toast.makeText(MainActivityForgotPass.this, "Bạn cần nhập email đã đăng ký", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivityForgotPass.this, "Đã gửi.", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivityForgotPass.this, "Gửi thất bại.", Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    private void setView() {
        btnSend = findViewById(R.id.btn_send);
        txtEmail = findViewById(R.id.email_forgot);
        toolbar = findViewById(R.id.toolbar_forgot);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
