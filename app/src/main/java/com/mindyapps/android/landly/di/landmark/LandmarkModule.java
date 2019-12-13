package com.mindyapps.android.landly.di.landmark;

import android.app.Application;

import com.bumptech.glide.RequestManager;
import com.mindyapps.android.landly.di.main.MainScope;
import com.mindyapps.android.landly.network.PixabayApi;
import com.mindyapps.android.landly.repositories.FavouritesRepository;
import com.mindyapps.android.landly.repositories.LandmarkRepository;
import com.mindyapps.android.landly.ui.random.RandomRecyclerAdapter;
import com.mindyapps.android.landly.ui.search.SearchRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public  class LandmarkModule {

    @LandmarkScope
    @Provides
    static FavouritesRepository provideFavouritesRepository(Application application) {
        return new FavouritesRepository(application);
    }

}
