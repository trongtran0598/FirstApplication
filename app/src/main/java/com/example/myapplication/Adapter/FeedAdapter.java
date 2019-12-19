package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Data.FeedModel;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<FeedModel> feedModelList;
    Context context;
    ItemClickListener listener;


    public interface ItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }


    public FeedAdapter(List<FeedModel> feedModelList, Context context) {
        this.feedModelList = feedModelList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView tvTitle, tvDecription;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageItem);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDecription = itemView.findViewById(R.id.tvDescription);
//            tvPubDate = itemView.findViewById(R.id.tvPubDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onClick(position);
                        }
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FeedModel feedModel = feedModelList.get(position);


        holder.tvTitle.setText(feedModel.getTitle());
        holder.tvDecription.setText(feedModel.getDescription());
        Picasso.get().load(feedModel.getImg()).resize(154,100).into(holder.imageView);


    }


    @Override
    public int getItemCount() {
        return feedModelList.size();
    }
}
