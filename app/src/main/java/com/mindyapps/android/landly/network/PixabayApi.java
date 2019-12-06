package com.mindyapps.android.landly.network;

import com.mindyapps.android.landly.models.Landmark;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {

    @GET(".")
    Call<Landmark> getLandmark(@Query("key") String apiKey,
                               @Query("q") String placeName);
}
