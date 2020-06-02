package com.marca.mobileproject.database.event;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.List;

class EventRepository {

    private EventDAO eventDAO;
    private LiveData<List<Event>> events;

    EventRepository(final Application application) {
        EventRoomDatabase db = EventRoomDatabase.getInstance(application);
        eventDAO = db.eventDAO();
        events = eventDAO.getEvents();
    }

    LiveData<List<Event>> getEvents() {
        return events;
    }

    LiveData<List<Event>> getEventsOfDay(final Calendar day) {
        return eventDAO.getEventsOfDay(day);
    }

}
