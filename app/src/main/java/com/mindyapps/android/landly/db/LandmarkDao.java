package com.mindyapps.android.landly.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LandmarkDao {

    @Insert
    void insert(LandmarkEntity note);

    @Query("SELECT * FROM landmark_table ORDER BY id DESC")
    LiveData<List<LandmarkEntity>> getAllLandmarks();

    @Query("DELETE FROM landmark_table WHERE pageUrl = :pageUrl")
    void deleteByPageUrl(String pageUrl);


    @Query("SELECT COUNT(id) FROM landmark_table WHERE pageUrl = :pageUrl")
    int getLandmarkByUrl(String pageUrl);
}
