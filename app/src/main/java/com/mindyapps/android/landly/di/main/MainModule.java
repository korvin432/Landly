package com.mindyapps.android.landly.di.main;

import com.mindyapps.android.landly.network.PixabayApi;
import com.mindyapps.android.landly.repositories.LandmarkRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static PixabayApi provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(PixabayApi.class);
    }


    @MainScope
    @Provides
    static LandmarkRepository provideLandMarksRepo(Retrofit retrofit, PixabayApi pixabayApi) {
        return new LandmarkRepository(retrofit, pixabayApi);
    }


}
