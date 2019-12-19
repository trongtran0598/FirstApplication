package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.MainActivityInfoAcc;
import com.example.myapplication.Activity.MainActivityLogin;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentComunity extends Fragment {
    FloatingActionButton login;
    RecyclerView recyclerView;
    Button logout;
    FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comunity, container,false);

        login = view.findViewById(R.id.btn_login);
        recyclerView = view.findViewById(R.id.recyclerViewComunity);
        logout = view.findViewById(R.id.alo);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = auth.getCurrentUser();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user != null){
                    Intent i = new Intent(getActivity(), MainActivityInfoAcc.class);
                    startActivity(i);
                    Toast.makeText(getContext(), user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), MainActivityLogin.class);
                    startActivity(intent);
                }
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
