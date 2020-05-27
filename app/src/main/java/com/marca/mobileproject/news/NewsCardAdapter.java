package com.marca.mobileproject.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marca.mobileproject.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsCardAdapter extends RecyclerView.Adapter<NewsCardHolder> {

    private List<News> newsList = new ArrayList<>();
    private OnNewsListener listener;

    public NewsCardAdapter(final OnNewsListener listener) {
        this.listener = listener;
    }

    /**
     *
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
     *
     * @param parent ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public NewsCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new NewsCardHolder(view, listener);
    }
    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the RecyclerView.ViewHolder.itemView to reflect
     * the item at the given position.
     *
     * @param holder ViewHolder which should be updated to represent the contents of the item at
     *               the given position in the data set.
     * @param position position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull NewsCardHolder holder, int position) {
        final News currentNews = newsList.get(position);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        final Date date = new Date(currentNews.getDate());
        holder.date.setText(sdf.format(date));
        holder.title.setText(currentNews.getTitle());
        holder.description.setText(currentNews.getDescription());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    /**
     * Method called when a new item is added
     * @param newData the new list of news
     */
    public void setData(List<News> newData) {
        this.newsList.clear();
        this.newsList.addAll(newData);
        notifyDataSetChanged();
    }
}
