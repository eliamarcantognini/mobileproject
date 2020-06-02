package com.marca.mobileproject.database;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Converters for EventRoomDatabase instance.
 */
public class Converters {
    @TypeConverter
    public static Calendar fromTimestampToCalendar(String value) {
        if(value == null) {
            return null;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(Long.parseLong(value)*1000);
        return cal;
    }

    @TypeConverter
    public static String fromCalendarToTimestamp(Calendar cal) {
        if(cal == null) {
            return null;
        }
        return "" + cal.getTimeInMillis()/1000;
    }
}