package com.mindyapps.android.landly.ui.random;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.repositories.LandmarkRepository;
import com.mindyapps.android.landly.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.support.DaggerFragment;

public class RandomFragment extends DaggerFragment {
    private static final String TAG = "RandomFragment";

    private RandomViewModel randomViewModel;
    private RecyclerView recyclerView;

    @Inject
    RandomRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    LandmarkRepository landmarkRepository;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_random, container, false);
        randomViewModel = ViewModelProviders.of(this, providerFactory).get(RandomViewModel.class);
        randomViewModel.init();

        recyclerView = root.findViewById(R.id.random_recycler);
        initRecyclerView();

        adapter.clear();
        randomViewModel.getLandmarkRepository().observe(this, new Observer<List<Landmark>>() {
            @Override
            public void onChanged(List<Landmark> landmarks) {
                if (landmarks != null && landmarks.size() >= landmarkRepository.getRandomLandMarksSize()) {
                    adapter.setLandmarks(landmarks);
                }
            }
        });

        return root;
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}