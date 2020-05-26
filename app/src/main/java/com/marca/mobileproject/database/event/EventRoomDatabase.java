package com.marca.mobileproject.database.event;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.marca.mobileproject.database.Converters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Event.class}, version=1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class EventRoomDatabase extends RoomDatabase {

    abstract EventDAO eventDAO();
    private static EventRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREAD = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);
    /**
     * Gets the singleton instance of EventRoomDatabase.
     *
     * @param context The context.
     * @return The singleton instance of EventRoomDatabase.
     */
    public  static EventRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (EventRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    /**
     *
     * @param context
     * @return
     */
    public static EventRoomDatabase buildDatabase(final Context context) {
        final Callback callback = new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                databaseWriteExecutor
                        .execute(() -> getInstance(context).eventDAO().insertAll(Event.getDummyData()));
            }
        };
        return Room.databaseBuilder(context,
                EventRoomDatabase.class,
                "event_db")
                .addCallback(callback)
                .build();
    }
}
