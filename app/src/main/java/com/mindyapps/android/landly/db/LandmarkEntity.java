package com.mindyapps.android.landly.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName =  "landmark_table")
public class LandmarkEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    private String userImageUrl;
    private String imageUrl;
    private int likes;
    private int views;

    public LandmarkEntity(String userName, String userImageUrl, String imageUrl, int likes, int views) {
        this.userName = userName;
        this.userImageUrl = userImageUrl;
        this.imageUrl = imageUrl;
        this.likes = likes;
        this.views = views;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }
}
