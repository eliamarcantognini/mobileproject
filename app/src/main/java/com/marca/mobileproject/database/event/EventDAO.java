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
     *
     * @return
     */
    @Transaction
    @Query("SELECT * from events ORDER BY calendar_day")
    LiveData<List<Event>> getEvents();

    /**
     *
     * @param day
     * @return
     */
    @Transaction
    @Query("SELECT * FROM events WHERE calendar_day LIKE :day")
    LiveData<List<Event>> getEventsOfDay(Calendar day);

    /**
     *
     * @param eventEntities
     * @return
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertAll(Event... eventEntities);

    /**
     *
     * @return
     */
    @Transaction
    @Query("SELECT COUNT(*) FROM events")
    int count();
}
