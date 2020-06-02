package com.marca.mobileproject.event;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marca.mobileproject.R;


/**
 * EventCard holder
 */
class EventCardHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView description;
    TextView date;
    TextView time;
    ImageView shareBtn;

    EventCardHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.event_title);
        description = itemView.findViewById(R.id.event_description);
        date = itemView.findViewById(R.id.event_date);
        time = itemView.findViewById(R.id.event_time);
        this.shareBtn = itemView.findViewById(R.id.shareIcon);
    }
}
