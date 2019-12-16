package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Data.Hero;
import com.example.myapplication.R;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.VIewHolder> {

    private List<Hero> heroList;
    Context context;

    public HeroAdapter(List<Hero> heroList, Context context) {
        this.heroList = heroList;
        this.context = context;
    }

    public class VIewHolder extends RecyclerView.ViewHolder {
        public de.hdodenhof.circleimageview.CircleImageView imageView;
        public TextView textView;

        public VIewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.profile_image);
            textView = itemView.findViewById(R.id.tv_name);

        }
    }

    @NonNull
    @Override
    public VIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_hero, parent, false);

        VIewHolder viewHolder = new VIewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VIewHolder holder, int position) {
        Hero hero = heroList.get(position);
        holder.imageView.setImageResource(hero.getImage());
        holder.textView.setText(hero.getName());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }
}
