package com.mindyapps.android.landly.di.main;

import androidx.lifecycle.ViewModel;

import com.mindyapps.android.landly.di.ViewModelKey;
import com.mindyapps.android.landly.ui.random.RandomViewModel;
import com.mindyapps.android.landly.ui.search.SearchViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(RandomViewModel.class)
    public abstract ViewModel bindRandomViewModel(RandomViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    public abstract ViewModel bindSearchViewModel(SearchViewModel viewModel);
}
