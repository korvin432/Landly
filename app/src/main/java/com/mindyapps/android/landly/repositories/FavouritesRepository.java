package com.mindyapps.android.landly.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mindyapps.android.landly.db.LandmarkDao;
import com.mindyapps.android.landly.db.LandmarkDatabase;
import com.mindyapps.android.landly.db.LandmarkEntity;
import com.mindyapps.android.landly.network.PixabayApi;

import java.util.List;

import javax.inject.Inject;

public class FavouritesRepository {

    private LandmarkDao landmarkDao;
    private LiveData<List<LandmarkEntity>> allLandmarks;

    @Inject
    public FavouritesRepository(Application application) {
        LandmarkDatabase database = LandmarkDatabase.getInstance(application);
        landmarkDao = database.landmarkDao();
        allLandmarks = landmarkDao.getAllLandmarks();
    }


    public void insert(LandmarkEntity landmarkEntity){
        new  InsertLandmarkAsyncTask(landmarkDao).execute(landmarkEntity);
    }

    public void delete(LandmarkEntity landmarkEntity){
        new  DeleteLandmarkAsyncTask(landmarkDao).execute(landmarkEntity);
    }

    public LiveData<List<LandmarkEntity>> getAllLandmarks() {
        return allLandmarks;
    }

    private static class InsertLandmarkAsyncTask extends AsyncTask<LandmarkEntity, Void, Void> {
        private LandmarkDao landmarkDao;

        private InsertLandmarkAsyncTask(LandmarkDao landmarkDao){
            this.landmarkDao = landmarkDao;
        }

        @Override
        protected Void doInBackground(LandmarkEntity... landmarkEntities) {
            landmarkDao.insert(landmarkEntities[0]);
            return null;
        }
    }

    private static class DeleteLandmarkAsyncTask extends AsyncTask<LandmarkEntity, Void, Void> {
        private LandmarkDao landmarkDao;

        private DeleteLandmarkAsyncTask(LandmarkDao landmarkDao){
            this.landmarkDao = landmarkDao;
        }

        @Override
        protected Void doInBackground(LandmarkEntity... landmarkEntities) {
            landmarkDao.delete(landmarkEntities[0]);
            return null;
        }
    }

}
