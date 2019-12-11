package com.mindyapps.android.landly.ui.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.repositories.LandmarkRepository;
import com.mindyapps.android.landly.util.Constants;

import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    private static final String TAG = "SearchViewModel";
    private LiveData<Landmark> landmarkMutableLiveData;
    private MutableLiveData<String> etText;
    @Inject
    LandmarkRepository landmarkRepository;


    public void init(){
        if (landmarkMutableLiveData != null){
            return;
        }
        etText = new MutableLiveData<>();
        landmarkMutableLiveData = new MutableLiveData<>();
        //getData(etText.getValue());
    }

    @Inject
    public SearchViewModel() {
    }

    public LiveData<Landmark> getData() {
        landmarkMutableLiveData = landmarkRepository.getLandmark();
        return landmarkMutableLiveData;
    }

    public void searchData(String query) {
        landmarkRepository.getLandmarksByName(Constants.PIXABAY_API_KEY, query);
        getData();
    }

    public LiveData<String> getText() {
        return etText;
    }

}