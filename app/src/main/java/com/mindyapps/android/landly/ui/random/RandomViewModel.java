package com.mindyapps.android.landly.ui.random;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.repositories.LandmarkRepository;
import com.mindyapps.android.landly.util.Constants;

import java.util.List;

import javax.inject.Inject;

public class RandomViewModel extends ViewModel {

    private MutableLiveData<List<Landmark>> landmarkMutableLiveData;
    @Inject
    LandmarkRepository landmarkRepository;

    public void init(){
        if (landmarkMutableLiveData != null){
            return;
        }
        landmarkMutableLiveData = landmarkRepository.getRandomLandmarks(Constants.PIXABAY_API_KEY);
    }

    @Inject
    public RandomViewModel() {
    }

    public LiveData<List<Landmark>> getLandmarkRepository() {
        return landmarkMutableLiveData;
    }

}