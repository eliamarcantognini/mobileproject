package com.marca.mobileproject.news;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marca.mobileproject.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsCardAdapter extends RecyclerView.Adapter<NewsCardHolder> implements Filterable {

    private List<News> newsList = new ArrayList<>();
    private List<News> newsListFull = new ArrayList<>();
    private OnNewsListener listener;
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<News> filteredNews = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredNews.addAll(newsListFull);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (News news : newsListFull) {
                    if (news.getTitle().toLowerCase().trim().contains(pattern) ||
                        news.getDescription().toLowerCase().trim().contains(pattern)) {
                        filteredNews.add(news);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredNews;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            newsList.clear();
            List<?> result = (List<?>) results.values;
            for (Object object : result) {
                newsList.add((News) object);
            }
            notifyDataSetChanged();
        }
    };

    NewsCardAdapter(final OnNewsListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new NewsCardHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCardHolder holder, int position) {
        final News currentNews = newsList.get(position);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        final Date date = new Date(currentNews.getDate());
        holder.date.setText(sdf.format(date));
        holder.title.setText(currentNews.getTitle());
        holder.description.setText(currentNews.getDescription());
        holder.shareBtn.setOnClickListener(v -> {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    v.getContext().getString(R.string.news_title) + " "
                            + currentNews.getTitle() + "\n"
                            + v.getContext().getString(R.string.news_description) + " " +
                    currentNews.getDescription() +
                            "\n" + v.getContext().getString(R.string.news_date) + " " +
                    sdf.format(currentNews.getDate()));

            sendIntent.setType("text/plain");
            if (v.getContext() != null &&
                    sendIntent.resolveActivity(v.getContext().getPackageManager()) != null) {
                v.getContext().startActivity(Intent.createChooser(sendIntent, null));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    void setData(List<News> newData) {
        this.newsList.clear();
        this.newsList.addAll(newData);
        this.newsListFull.clear();
        this.newsListFull.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
}
