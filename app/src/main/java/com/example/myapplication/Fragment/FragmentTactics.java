package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class FragmentTactics extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tactics, container,false);
        return view;
    }

    public static FragmentTactics newInstance() {
        Bundle args = new Bundle();
        FragmentTactics fragment = new FragmentTactics();
        fragment.setArguments(args);
        return fragment;
    }
}
