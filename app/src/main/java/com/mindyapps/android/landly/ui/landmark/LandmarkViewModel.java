package com.mindyapps.android.landly.ui.landmark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.util.Constants;

import java.util.List;

import javax.inject.Inject;

public class LandmarkViewModel extends ViewModel {

    private MutableLiveData<Landmark> landmarkMutableLiveData;

    public void init(){
        if (landmarkMutableLiveData != null){
            return;
        }
    }

    @Inject
    public LandmarkViewModel() {
    }

}