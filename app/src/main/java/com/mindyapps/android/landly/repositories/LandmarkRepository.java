package com.mindyapps.android.landly.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.network.PixabayApi;
import com.mindyapps.android.landly.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class LandmarkRepository {

    private static final String TAG = "LandmarkRepository";

    private List<String> randomMarks = new ArrayList<>();
    private PixabayApi pixabayApi;
    private int randomSize;

    private MutableLiveData<Landmark> landmarkMutableLiveData = new MutableLiveData<>();

    @Inject
    public LandmarkRepository(PixabayApi pixabayApi) {
        this.pixabayApi = pixabayApi;
    }

    public LiveData<Landmark> getLandmark() {
        return landmarkMutableLiveData;
    }

    public LiveData<Landmark> getLandmarksByName(String key, String name) {
        pixabayApi.getLandmark(key, name, 64).enqueue(new Callback<Landmark>() {
            @Override
            public void onResponse(Call<Landmark> call, Response<Landmark> response) {
                if (response.isSuccessful()) {
                    landmarkMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Landmark> call, Throwable t) {
                landmarkMutableLiveData.postValue(null);
            }
        });
        return landmarkMutableLiveData;
    }

    public MutableLiveData<List<Landmark>> getRandomLandmarks(String key) {
        setRandomLandMarksSize(0);
        final MutableLiveData<List<Landmark>> landmarksData = new MutableLiveData<>();
        final List<Landmark> landmarks = new ArrayList<>();
        final List<String> usedMarks = new ArrayList<>();
        final List<String> randomLandmarks = getRandomMarks();

        for (int i = 0; i<randomLandmarks.size(); i++) {
            final int finalI = i;
            pixabayApi.getLandmark(key, randomLandmarks.get(i), 3).enqueue(new Callback<Landmark>() {
                @Override
                public void onResponse(Call<Landmark> call, Response<Landmark> response) {
                    if (response.isSuccessful() && response.body().getHitList() != null) {
                        if (!usedMarks.contains(randomLandmarks.get(finalI)) && response.body().getImageUrl() != null) {
                            response.body().setName(randomLandmarks.get(finalI));
                            landmarks.add(response.body());

                            landmarksData.setValue(landmarks);
                            usedMarks.add(randomLandmarks.get(finalI));
                        }
                    }
                }

                @Override
                public void onFailure(Call<Landmark> call, Throwable t) {
                    landmarksData.setValue(null);
                }
            });
            if (landmarksData.getValue() != null) {
                setRandomLandMarksSize(landmarksData.getValue().size());
            }
        }
        return landmarksData;
    }

    public void setRandomLandMarksSize(int randomSize) {
        this.randomSize = randomSize;
    }

    public int getRandomLandMarksSize(){
        randomSize++;
        return randomSize++;
    }

    public List<String> getRandomMarks() {
        Random rand = new Random();
        List<String> randomLandmarks = new ArrayList<>();
        randomMarks = Arrays.asList(Constants.LANDMARKS.split("\\s*,\\s*"));
        for (int i = 0; i < 15; i++) {
            randomLandmarks.add(randomMarks.get(rand.nextInt(randomMarks.size())));
        }
        return randomLandmarks;
    }
}
