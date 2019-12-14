package com.mindyapps.android.landly.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName =  "landmark_table")
public class LandmarkEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    private String landmarkName;
    private String userImageUrl;
    private String imageUrl;
    private int likes;
    private int views;

    public LandmarkEntity(String userName, String landmarkName, String userImageUrl, String imageUrl, int likes, int views) {
        this.userName = userName;
        this.landmarkName = landmarkName;
        this.userImageUrl = userImageUrl;
        this.imageUrl = imageUrl;
        this.likes = likes;
        this.views = views;
    }

    protected LandmarkEntity(Parcel in) {
        id = in.readInt();
        userName = in.readString();
        landmarkName = in.readString();
        userImageUrl = in.readString();
        imageUrl = in.readString();
        likes = in.readInt();
        views = in.readInt();
    }

    public static final Creator<LandmarkEntity> CREATOR = new Creator<LandmarkEntity>() {
        @Override
        public LandmarkEntity createFromParcel(Parcel in) {
            return new LandmarkEntity(in);
        }

        @Override
        public LandmarkEntity[] newArray(int size) {
            return new LandmarkEntity[size];
        }
    };

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

    public String getLandmarkName() {
        return landmarkName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(userName);
        dest.writeString(landmarkName);
        dest.writeString(userImageUrl);
        dest.writeString(imageUrl);
        dest.writeInt(likes);
        dest.writeInt(views);
    }
}
