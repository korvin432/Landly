package com.mindyapps.android.landly.di.main;

import com.mindyapps.android.landly.ui.favourite.FavouriteFragment;
import com.mindyapps.android.landly.ui.random.RandomFragment;
import com.mindyapps.android.landly.ui.search.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract RandomFragment contributeRandomFragment();

    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

    @ContributesAndroidInjector
    abstract FavouriteFragment contributeFavouriteFragment();

}
