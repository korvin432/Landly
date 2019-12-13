package com.mindyapps.android.landly.ui.landmark;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mindyapps.android.landly.db.LandmarkEntity;
import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.repositories.FavouritesRepository;
import com.mindyapps.android.landly.repositories.LandmarkRepository;
import com.mindyapps.android.landly.util.Constants;

import java.util.List;

import javax.inject.Inject;

public class LandmarkViewModel extends ViewModel {

    @Inject
    FavouritesRepository favouritesRepository;

    private LiveData<List<LandmarkEntity>> allLandmarks;

    @Inject
    public LandmarkViewModel() {
    }

    public void insert(LandmarkEntity landmarkEntity){
        favouritesRepository.insert(landmarkEntity);
    }

    public void delete(LandmarkEntity landmarkEntity){
        favouritesRepository.delete(landmarkEntity);
    }

    public LiveData<List<LandmarkEntity>> getAllLandmarkEntities() {
        allLandmarks = favouritesRepository.getAllLandmarks();
        return allLandmarks;
    }
}
