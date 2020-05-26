package com.marca.mobileproject.event;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marca.mobileproject.R;


public class EventCardHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView description;
    TextView time;

    public EventCardHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.event_title);
        description = itemView.findViewById(R.id.event_description);
        time = itemView.findViewById(R.id.event_time);
    }
}
