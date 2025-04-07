package com.example.taskmanagement.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.util.Converters;

@Database(entities = {Task.class}, version = 1, exportSchema = true)
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static volatile TaskDatabase INSTANCE;

    public static TaskDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                INSTANCE = Room.databaseBuilder(
                                context.getApplicationContext(),
                                TaskDatabase.class,
                                "task_db"
                        ).allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }
}