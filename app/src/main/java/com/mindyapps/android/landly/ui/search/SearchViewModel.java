package com.mindyapps.android.landly.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.repositories.LandmarkRepository;
import com.mindyapps.android.landly.util.Constants;

import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<Landmark> landmarkMutableLiveData;
    @Inject
    LandmarkRepository landmarkRepository;

    public void init(){
        if (landmarkMutableLiveData != null){
            return;
        }
    }

    @Inject
    public SearchViewModel() {
    }

    public LiveData<Landmark> getData(String query) {
        landmarkMutableLiveData = landmarkRepository.getLandmarksByName(Constants.PIXABAY_API_KEY, query);

        return landmarkMutableLiveData;
    }
}