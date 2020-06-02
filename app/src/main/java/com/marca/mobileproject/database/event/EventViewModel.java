package com.marca.mobileproject.database.event;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private EventRepository repository;

    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = new EventRepository(application);
    }

    public LiveData<List<Event>> getEvents() {
        return repository.getEvents();
    }

    public LiveData<List<Event>> getEventsOfDay(final Calendar day){
        return repository.getEventsOfDay(day);
    }

}
