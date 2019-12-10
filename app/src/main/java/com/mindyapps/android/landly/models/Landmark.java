package com.mindyapps.android.landly.models;

import com.google.gson.annotations.SerializedName;
import com.mindyapps.android.landly.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Landmark {
    private String name;
    private String imageUrl;
    @SerializedName("hits")
    private List<Hit> hitList;

    public Landmark(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        if (hitList != null && !hitList.isEmpty()) {
            return hitList.get(0).getImageUrl();
        }
        return null;
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

    public List<Hit> getHitList() {
        return hitList;
    }
}

class Hit{
    @SerializedName("webformatURL")
    private String imageUrl;

    @SerializedName("previewURL")
    private String previewUrl;

    public Hit(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }
}
