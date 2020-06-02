package com.marca.mobileproject.database.event;

import com.applandeo.materialcalendarview.EventDay;
import com.marca.mobileproject.R;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Events table
 */
@Entity(tableName = "events")
public class Event{


    /**
     * The date of the event.
     */
    @ColumnInfo(name = "calendar_day")
    private Calendar day;
    /**
     * The time of the event.
     */
    @ColumnInfo(name = "time")
    private String time;
    /**
     * The title of the event.
     */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String title;
    /**
     * The description of the event.
     */
    @ColumnInfo(name = "description")
    private String description;

    @Ignore
    private EventDay eventDay;

    public Event(final Calendar day, final String time, @NotNull final String title, final String description) {
        this.time = time;
        this.day = day;
        this.eventDay = new EventDay(day, R.drawable.ic_fiber_manual_record_24dp);
        this.title = title;
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    @SuppressWarnings("unused")
    public void setTime(final String time) {
        this.time = time;
    }

    Calendar getDay() {
        return day;
    }

    @SuppressWarnings("unused")
    public void setDay(final Calendar day) {
        this.day = day;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public EventDay getEventDay() {
        return eventDay;
    }

    @SuppressWarnings("unused")
    public void setEventDay(final EventDay eventDay) {
        this.eventDay = eventDay;
    }

    /**
     * Create an array of dummy Event with pattern [ Date, Time, Title n, An extraordinary event n ]
     * @return
     *      An array of dummy Events
     */
    static Event[] getDummyData() {
        return new Event[]{
                new Event(new GregorianCalendar(2020, 4, 22), "12:00",
                        "Title 1", "An extraordinary event" +  "1"),
                new Event(new GregorianCalendar(2020, 4, 28), "15:00",
                        "Title 2", "An extraordinary event 2"),
                new Event(new GregorianCalendar(2020, 5, 1), "15:00",
                        "Title 3", "An extraordinary event 3"),
                new Event(new GregorianCalendar(2020, 4, 24), "18:00",
                        "Title 4", "An extraordinary event 4"),
                new Event(new GregorianCalendar(2020, 4, 22), "19:30",
                        "Title 5", "An extraordinary event 5"),
                new Event(new GregorianCalendar(2020, 5, 24), "12:30",
                        "Title 6", "An extraordinary event 6")
        };
    }

    @NotNull
    @Override
    public String toString() {
        return "Event{" +
                "day=" + day +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", eventDay=" + eventDay +
                '}';
    }
}
