package com.mindyapps.android.landly.di;

import com.mindyapps.android.landly.MainActivity;
import com.mindyapps.android.landly.di.main.MainFragmentBuildersModule;
import com.mindyapps.android.landly.di.main.MainModule;
import com.mindyapps.android.landly.di.main.MainScope;
import com.mindyapps.android.landly.di.main.MainViewModelsModule;
import com.mindyapps.android.landly.ui.landmark.LandmarkActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector()
    abstract LandmarkActivity contributeLandmarkActivity();

}
