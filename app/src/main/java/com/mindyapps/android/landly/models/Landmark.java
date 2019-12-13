package com.mindyapps.android.landly.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.mindyapps.android.landly.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Landmark implements Parcelable {
    private String name;
    private String imageUrl;
    @SerializedName("hits")
    private List<Hit> hitList;

    public Landmark(String name, String imageUrl, List<Hit> hitList) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.hitList = hitList;
    }

    protected Landmark(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        hitList = new ArrayList<>();
        in.readList(hitList, Hit.class.getClassLoader());
    }

    public static final Creator<Landmark> CREATOR = new Creator<Landmark>() {
        @Override
        public Landmark createFromParcel(Parcel in) {
            return new Landmark(in);
        }

        @Override
        public Landmark[] newArray(int size) {
            return new Landmark[size];
        }
    };

    public String getImageUrl(int i) {
        if (hitList != null && !hitList.isEmpty()) {
            return hitList.get(i).getImageUrl();
        }
        return null;
    }

    public int getViews(int i) {
        return hitList.get(i).getViewsCount();
    }

    public int getLikes(int i) {
        return hitList.get(i).getLikesCount();
    }

    public String getUserName(int i) {
        return hitList.get(i).getUserName();
    }

    public String getUserImage(int i) {
        return hitList.get(i).getUserImage();
    }

    public String getPreviewUrl(int position) {
        return hitList.get(position).getPreviewUrl();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPageUrl(int i) {
        return hitList.get(i).getPageUrl();
    }

    public List<Hit> getHitList() {
        return hitList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeList(hitList);
    }
}

class Hit implements Parcelable{
    @SerializedName("pageURL")
    private String pageUrl;

    @SerializedName("webformatURL")
    private String imageUrl;

    @SerializedName("previewURL")
    private String previewUrl;

    @SerializedName("views")
    private int viewsCount;

    @SerializedName("likes")
    private int likesCount;

    @SerializedName("user")
    private String userName;

    @SerializedName("userImageURL")
    private String userImage;

    public Hit(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    protected Hit(Parcel in) {
        pageUrl = in.readString();
        imageUrl = in.readString();
        previewUrl = in.readString();
        viewsCount = in.readInt();
        likesCount = in.readInt();
        userName = in.readString();
        userImage = in.readString();
    }

    public static final Creator<Hit> CREATOR = new Creator<Hit>() {
        @Override
        public Hit createFromParcel(Parcel in) {
            return new Hit(in);
        }

        @Override
        public Hit[] newArray(int size) {
            return new Hit[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pageUrl);
        dest.writeString(imageUrl);
        dest.writeString(previewUrl);
        dest.writeInt(viewsCount);
        dest.writeInt(likesCount);
        dest.writeString(userName);
        dest.writeString(userImage);
    }
}
