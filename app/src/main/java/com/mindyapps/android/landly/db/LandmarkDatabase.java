package com.mindyapps.android.landly.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = LandmarkEntity.class, version = 1)
public abstract class LandmarkDatabase extends RoomDatabase {

    private static LandmarkDatabase instance;

    public abstract LandmarkDao landmarkDao();

    public static synchronized LandmarkDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LandmarkDatabase.class, "landmark_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
