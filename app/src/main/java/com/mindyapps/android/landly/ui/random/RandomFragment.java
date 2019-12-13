package com.mindyapps.android.landly.ui.random;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.repositories.LandmarkRepository;
import com.mindyapps.android.landly.ui.landmark.LandmarkActivity;
import com.mindyapps.android.landly.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.mindyapps.android.landly.util.Constants.EXTRA_LANDMARK_IMAGE_TRANSITION_NAME;
import static com.mindyapps.android.landly.util.Constants.LANDMARK_EXTRA;

public class RandomFragment extends DaggerFragment implements OnLandmarkListener{
    private static final String TAG = "RandomFragment";

    private RandomViewModel randomViewModel;
    private RecyclerView recyclerView;

    @Inject
    RandomRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    LandmarkRepository landmarkRepository;

    @Inject
    public RandomFragment(){
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_random, container, false);
        randomViewModel = ViewModelProviders.of(this, providerFactory).get(RandomViewModel.class);
        randomViewModel.init();

        recyclerView = root.findViewById(R.id.random_recycler);
        initRecyclerView();

        adapter.clear();
        adapter.setOnItemClickListener(this);
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

    @Override
    public void onLandmarkClick(int position, ImageView sharedImageView) {
        Landmark landmark = adapter.getSelectedLandmark(position);
        Intent intent = new Intent(getActivity(), LandmarkActivity.class);
        intent.putExtra(LANDMARK_EXTRA, landmark);
        intent.putExtra(EXTRA_LANDMARK_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        startActivity(intent, options.toBundle());
    }
}