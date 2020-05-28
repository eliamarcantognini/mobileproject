package com.marca.mobileproject.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marca.mobileproject.R;

public class NewsCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title;
    TextView description;
    TextView date;
    ImageView shareBtn;
    private OnNewsListener newsListener;

    NewsCardHolder(@NonNull View itemView, final OnNewsListener listener) {
        super(itemView);
        this.title = itemView.findViewById(R.id.news_title);
        this.description = itemView.findViewById(R.id.news_description);
        this.date = itemView.findViewById(R.id.news_date);
        this.newsListener = listener;
        this.shareBtn = itemView.findViewById(R.id.shareIcon);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        newsListener.onNewsClick(getAdapterPosition());
    }

}
