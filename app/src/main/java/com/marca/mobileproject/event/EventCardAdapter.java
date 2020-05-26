package com.marca.mobileproject.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marca.mobileproject.R;
import com.marca.mobileproject.database.event.Event;

import java.util.ArrayList;
import java.util.List;

public class EventCardAdapter extends RecyclerView.Adapter<EventCardHolder> {


    private List<Event> eventList = new ArrayList<>();

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
    public EventCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent, false);
        return new EventCardHolder(view);
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
    public void onBindViewHolder(@NonNull EventCardHolder holder, int position) {
        final Event currentEvent = eventList.get(position);
        holder.title.setText(currentEvent.getTitle());
        holder.description.setText(currentEvent.getDescription());
        holder.time.setText(currentEvent.getTime());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    /**
     * Method called when a new item is added
     * @param newData the new list of events
     */
    public void setData(List<Event> newData) {
        this.eventList.clear();
        this.eventList.addAll(newData);
        notifyDataSetChanged();
    }
}
