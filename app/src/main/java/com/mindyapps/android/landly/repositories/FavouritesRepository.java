package com.mindyapps.android.landly.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mindyapps.android.landly.db.LandmarkDao;
import com.mindyapps.android.landly.db.LandmarkDatabase;
import com.mindyapps.android.landly.db.LandmarkEntity;
import com.mindyapps.android.landly.network.PixabayApi;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public void delete(String imageUrl){
        new  DeleteLandmarkAsyncTask(landmarkDao).execute(imageUrl);
    }

    public int getLandmarkByUrl(String imageUrl){
        try {
            return new LandmarkByUrlAsyncTask(landmarkDao).execute(imageUrl).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
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

    private static class DeleteLandmarkAsyncTask extends AsyncTask<String, Void, Void> {
        private LandmarkDao landmarkDao;

        private DeleteLandmarkAsyncTask(LandmarkDao landmarkDao){
            this.landmarkDao = landmarkDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            landmarkDao.deleteByImageUrl(strings[0]);
            return null;
        }
    }

    private static class LandmarkByUrlAsyncTask extends AsyncTask<String, Void, Integer> {
        private LandmarkDao landmarkDao;

        private LandmarkByUrlAsyncTask(LandmarkDao landmarkDao){
            this.landmarkDao = landmarkDao;
        }

        @Override
        protected Integer doInBackground(String... strings) {
            return landmarkDao.getLandmarkByUrl(strings[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }

}
