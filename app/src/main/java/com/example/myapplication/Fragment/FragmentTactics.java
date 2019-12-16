package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.HeroAdapter;
import com.example.myapplication.Data.Hero;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentTactics extends Fragment {
    private RecyclerView recyclerView;
    private List<Hero> heroList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tactics, container, false);
        setData();
        recyclerView = view.findViewById(R.id.recyclerViewTatics);
        HeroAdapter heroAdapter = new HeroAdapter(heroList, getContext());
        recyclerView.setAdapter(heroAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),5));

        return view;
    }

    private void setData() {
        Hero h1 = new Hero(R.drawable.chim5, "Sivir ", "Blademaster ", "desert", 3);
        Hero h2 = new Hero(R.drawable.sivir, "Sivir ", "Blademaster ", "desert", 3);
        Hero h3 = new Hero(R.drawable.sivir, "Sivir ", "Blademaster ", "desert", 3);
        Hero h4 = new Hero(R.drawable.sivir, "Sivir ", "Blademaster ", "desert", 3);
        Hero h5 = new Hero(R.drawable.sivir, "Sivir ", "Blademaster ", "desert", 3);
        heroList = new ArrayList<>();
        heroList.add(h1);
        heroList.add(h2);
        heroList.add(h3);
        heroList.add(h4);
        heroList.add(h5);


    }

    public static FragmentTactics newInstance() {
        Bundle args = new Bundle();
        FragmentTactics fragment = new FragmentTactics();
        fragment.setArguments(args);
        return fragment;
    }
}
