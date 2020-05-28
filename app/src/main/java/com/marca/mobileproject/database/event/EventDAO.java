package com.marca.mobileproject.database.event;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.Calendar;
import java.util.List;

@Dao
public interface EventDAO {

    /**
     * Select all events from table ordered by @calendar_day.
     * @return
     *      Events ordered by day.
     */
    @Transaction
    @Query("SELECT * from events ORDER BY calendar_day")
    LiveData<List<Event>> getEvents();

    /**
     * Select all events from table with given @calendar_day
     * @param day
     *      The day wanted.
     * @return
     *      The events.
     */
    @Transaction
    @Query("SELECT * FROM events WHERE calendar_day LIKE :day")
    LiveData<List<Event>> getEventsOfDay(Calendar day);

    /**
     * Insert all events given in the table.
     * @param eventEntities
     *      Events to be added.
     * @return
     *      Events uid if added successfully, -1 otherwise.
     */
    @SuppressWarnings("UnusedReturnValue")
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertAll(Event... eventEntities);
}
