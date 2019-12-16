package com.mindyapps.android.landly.di.landmark;

import androidx.lifecycle.ViewModel;

import com.mindyapps.android.landly.di.ViewModelKey;
import com.mindyapps.android.landly.ui.landmark.LandmarkViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class LandmarkViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(LandmarkViewModel.class)
    public abstract ViewModel bindLandmarkViewModel(LandmarkViewModel viewModel);
}
