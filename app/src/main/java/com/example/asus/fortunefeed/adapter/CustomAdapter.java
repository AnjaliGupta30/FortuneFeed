package com.example.asus.fortunefeed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asus.fortunefeed.R;
import com.example.asus.fortunefeed.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 12-01-2019.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<News> dataList;

    public CustomAdapter(Context context, ArrayList<News> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle, desc, author, published;
        ImageView coverImage;
        ProgressBar progressBar;


        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
            desc = mView.findViewById(R.id.desc);
            author = mView.findViewById(R.id.author);
            published = mView.findViewById(R.id.publishedAt);

            progressBar = mView.findViewById(R.id.progress_bar);
            coverImage = mView.findViewById(R.id.img);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        String urlToImg = dataList.get(position).getUrlToImage();
        Picasso.with(context).load(urlToImg).into(holder.coverImage);

        holder.txtTitle.setText(dataList.get(position).getTitle());
        holder.desc.setText(dataList.get(position).getDescription());
        holder.author.setText(dataList.get(position).getAuthor());
        holder.published.setText(dataList.get(position).getPublishedAt());
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }


}
