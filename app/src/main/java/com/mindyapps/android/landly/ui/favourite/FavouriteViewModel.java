package com.mindyapps.android.landly.ui.favourite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mindyapps.android.landly.db.LandmarkEntity;
import com.mindyapps.android.landly.repositories.FavouritesRepository;
import com.mindyapps.android.landly.util.Constants;

import java.util.List;

import javax.inject.Inject;

public class FavouriteViewModel extends ViewModel {

    @Inject
    FavouritesRepository favouritesRepository;

    private LiveData<List<LandmarkEntity>> allLandmarks;

    @Inject
    public FavouriteViewModel() {
    }

    public LiveData<List<LandmarkEntity>> getAllLandmarkEntities() {
        allLandmarks = favouritesRepository.getAllLandmarks();
        return allLandmarks;
    }

}