package com.mindyapps.android.landly.ui.favourite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.db.LandmarkEntity;
import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.ui.landmark.LandmarkActivity;
import com.mindyapps.android.landly.ui.random.OnLandmarkListener;
import com.mindyapps.android.landly.ui.random.RandomRecyclerAdapter;
import com.mindyapps.android.landly.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.mindyapps.android.landly.util.Constants.EXTRA_LANDMARK_IMAGE_TRANSITION_NAME;
import static com.mindyapps.android.landly.util.Constants.LANDMARK_ENTITY_EXTRA;
import static com.mindyapps.android.landly.util.Constants.LANDMARK_EXTRA;

public class FavouriteFragment extends DaggerFragment implements OnLandmarkListener {

    private FavouriteViewModel notificationsViewModel;
    private RecyclerView recyclerView;

    @Inject
    FavouriteRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this, providerFactory).get(FavouriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);

        recyclerView = root.findViewById(R.id.favourite_recycler);
        adapter.setOnItemClickListener(this);
        initRecyclerView();

        notificationsViewModel.getAllLandmarkEntities().observe(this, new Observer<List<LandmarkEntity>>() {
            @Override
            public void onChanged(List<LandmarkEntity> landmarkEntities) {
                adapter.setLandmarks(landmarkEntities);
            }
        });

        return root;
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLandmarkClick(int position, ImageView sharedImageView) {
        LandmarkEntity landmarkEntity = adapter.getSelectedLandmark(position);
        Intent intent = new Intent(getActivity(), LandmarkActivity.class);

        intent.putExtra(LANDMARK_ENTITY_EXTRA, landmarkEntity);
        intent.putExtra(EXTRA_LANDMARK_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        startActivity(intent, options.toBundle());
    }
}