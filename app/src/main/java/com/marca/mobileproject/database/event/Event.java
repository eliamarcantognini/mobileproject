package com.marca.mobileproject.database.event;

import com.applandeo.materialcalendarview.EventDay;
import com.marca.mobileproject.R;
import com.marca.mobileproject.database.Converters;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity(tableName = "events")
public class Event{


    @ColumnInfo(name = "calendar_day")
    private Calendar day;
    @ColumnInfo(name = "time")
    private String time;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;

    @Ignore
    EventDay eventDay;

    public Event(final Calendar day, final String time, final String title, final String description) {
        this.time = time;
        this.day = day;
        this.eventDay = new EventDay(day, R.drawable.ic_fiber_manual_record_24dp);
        this.title = title;
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    Calendar getDay() {
        return day;
    }

    public void setDay(Calendar day) {
        this.day = day;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventDay getEventDay() {
        return eventDay;
    }

    public void setEventDay(EventDay eventDay) {
        this.eventDay = eventDay;
    }

    public static Event[] getDummyData() {
        return new Event[]{
                new Event(new GregorianCalendar(2020, 4, 22), "12:00",
                        "Title 1", "An extraordinary event 1"),
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

//    public long getDaytimeTimestamp() {
////        String daytime = Converters.fromCalendarToTimestamp(this.day);
//////        Integer.parseInt(this.time.substring(0, 2)), Integer.parseInt(this.time.substring(3, 5);
////        Calendar date = null;
////        date.set(Calendar.HOUR, Integer.parseInt(this.time.substring(0, 2)));
////        date.set(Calendar.MINUTE, Integer.parseInt(this.time.substring(3, 5));
//        return Timestamp.valueOf(
//                new SimpleDateFormat("yyyy-MM-dd ")
//                        .format(new Date(Long.parseLong(Converters.fromCalendarToTimestamp(this.day))))
//                        .concat(this.time)).getTime();
//    }
}
