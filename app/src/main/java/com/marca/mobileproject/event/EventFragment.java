package com.marca.mobileproject.event;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marca.mobileproject.R;
import com.marca.mobileproject.database.event.Event;
import com.marca.mobileproject.database.event.EventViewModel;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class EventFragment extends Fragment {

    private EventViewModel eventViewModel;
    private EventCardAdapter adapter;
    private FloatingActionButton fab;
//    private OnClickListener listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.event_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();

        if (activity != null) {
            List<EventDay> dayEvents = new ArrayList<>();
            CalendarView calendarView = activity.findViewById(R.id.calendarView);

            fab = activity.findViewById(R.id.fab_fav);

            RecyclerView recyclerView = activity.findViewById(R.id.recycler);
            recyclerView.setHasFixedSize(true);
            adapter = new EventCardAdapter();
            recyclerView.setAdapter(adapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(layoutManager);


            /**
             * Date click listener
             */
            calendarView.setOnDayClickListener(eventDay -> {
                eventViewModel.getEventsOfDay(eventDay.getCalendar()).observe(activity, events -> {
                    if (!events.isEmpty()) {
                        fab.setVisibility(View.VISIBLE);
                        fab.setClickable(true);
                        addListener(events);
                    } else {
                        fab.setVisibility(View.INVISIBLE);
                        fab.setClickable(false);
                    }
                    adapter.setData(events);
                });
            });
            /**
             * Populate calendar
             */
            eventViewModel = new ViewModelProvider.AndroidViewModelFactory(activity.getApplication()).create(EventViewModel.class);
            eventViewModel.getEvents().observe(activity, events -> {
                    for (Event event : events) {
                        dayEvents.add(event.getEventDay());
                    }
                    calendarView.setEvents(dayEvents);
            });
        }
    }

    private void addListener(final List<Event> events) {

        fab.setOnClickListener(v -> {
            for (Event event : events) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, event.getTitle());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, event.getDescription());
//                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.getDaytimeTimestamp());
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.getEventDay().getCalendar().getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, event.getEventDay().getCalendar().getTimeInMillis() + 1440000);
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
                intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_FREE);

                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
