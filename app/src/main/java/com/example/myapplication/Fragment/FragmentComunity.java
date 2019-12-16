package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Activity.MainActivityLogin;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentComunity extends Fragment {
    FloatingActionButton login;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comunity, container,false);

        login = view.findViewById(R.id.btn_login);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivityLogin.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public static FragmentComunity newInstance() {
        Bundle args = new Bundle();
        FragmentComunity fragment = new FragmentComunity();
        fragment.setArguments(args);
        return fragment;
    }
}
