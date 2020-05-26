package com.marca.mobileproject.database.event;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.List;

public class EventRepository {
    private EventDAO eventDAO;
    private LiveData<List<Event>> events;

    public EventRepository(final Application application) {
        EventRoomDatabase db = EventRoomDatabase.getInstance(application);
        eventDAO = db.eventDAO();
        events = eventDAO.getEvents();
    }

    public LiveData<List<Event>> getEvents() {
        return events;
    }

    public LiveData<List<Event>> getEventsOfDay(final Calendar day) {
        return eventDAO.getEventsOfDay(day);
    }

}
