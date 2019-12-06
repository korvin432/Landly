package com.mindyapps.android.landly.di;

import androidx.lifecycle.ViewModelProvider;

import com.mindyapps.android.landly.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);

}
