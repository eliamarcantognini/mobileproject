package com.marca.mobileproject.news;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marca.mobileproject.R;

public class NewsCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title;
    TextView description;
    TextView date;
    private OnNewsListener newsListener;

    public NewsCardHolder(@NonNull View itemView, final OnNewsListener listener) {
        super(itemView);
        this.title = itemView.findViewById(R.id.news_title);
        this.description = itemView.findViewById(R.id.news_description);
        this.date = itemView.findViewById(R.id.news_date);
        this.newsListener = listener;
        itemView.setOnClickListener(this);
    }

    /**
     * implementation of method in View.OnclickListener
     * @param v the view
     */
    @Override
    public void onClick(final View v) {
        newsListener.onNewsClick(getAdapterPosition());
    }

}
