package com.mindyapps.android.landly.di.main;

import android.app.Application;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mindyapps.android.landly.MainActivity;
import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.network.PixabayApi;
import com.mindyapps.android.landly.repositories.LandmarkRepository;
import com.mindyapps.android.landly.ui.favourite.FavouriteRecyclerAdapter;
import com.mindyapps.android.landly.ui.random.OnLandmarkListener;
import com.mindyapps.android.landly.ui.random.RandomFragment;
import com.mindyapps.android.landly.ui.random.RandomRecyclerAdapter;
import com.mindyapps.android.landly.ui.search.SearchRecyclerAdapter;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public  class MainModule {

    @MainScope
    @Provides
    static RandomRecyclerAdapter provideRandomAdapter(RequestManager requestManager){
        return new RandomRecyclerAdapter(requestManager);
    }

    @MainScope
    @Provides
    static SearchRecyclerAdapter provideSearchAdapter(RequestManager requestManager){
        return new SearchRecyclerAdapter(requestManager);
    }

    @MainScope
    @Provides
    static FavouriteRecyclerAdapter provideFavouriteAdapter(RequestManager requestManager){
        return new FavouriteRecyclerAdapter(requestManager);
    }

    @MainScope
    @Provides
    static PixabayApi provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(PixabayApi.class);
    }

    @MainScope
    @Provides
    static LandmarkRepository provideLandMarksRepo(PixabayApi pixabayApi) {
        return new LandmarkRepository(pixabayApi);
    }

}
