package com.mindyapps.android.landly.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.ui.landmark.LandmarkActivity;
import com.mindyapps.android.landly.ui.random.OnLandmarkListener;
import com.mindyapps.android.landly.ui.random.RandomRecyclerAdapter;
import com.mindyapps.android.landly.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.mindyapps.android.landly.util.Constants.EXTRA_LANDMARK_IMAGE_TRANSITION_NAME;
import static com.mindyapps.android.landly.util.Constants.LANDMARK_EXTRA;
import static com.mindyapps.android.landly.util.Constants.POSITION_EXTRA;

public class SearchFragment extends DaggerFragment implements OnLandmarkListener {

    private static final String TAG = "SearchFragment";
    private SearchViewModel searchViewModel;
    private SearchView searchView;
    private RecyclerView recyclerView;

    @Inject
    SearchRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        searchViewModel = ViewModelProviders.of(this, providerFactory).get(SearchViewModel.class);
        searchViewModel.init();

        searchView = root.findViewById(R.id.et_search);
        recyclerView = root.findViewById(R.id.search_recycler);

        adapter.setOnItemClickListener(this);
        initRecyclerView();
        subscribeObservers();
        initSearchView();

        return root;
    }

    private void subscribeObservers() {
        searchViewModel.getData().observe(getViewLifecycleOwner(), new Observer<Landmark>() {
            @Override
            public void onChanged(Landmark landmark) {
                if (landmark != null) {
                    adapter.clear();
                    adapter.setLandmarks(landmark);
                }
            }
        });

        searchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                searchView.setQuery(s, true);
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchViewModel.searchData(s);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }


    @Override
    public void onLandmarkClick(int position, ImageView sharedImageView) {
        Landmark landmark = adapter.getSelectedLandmark();
        Intent intent = new Intent(getActivity(), LandmarkActivity.class);
        intent.putExtra(LANDMARK_EXTRA, landmark);
        intent.putExtra(POSITION_EXTRA, position);
        intent.putExtra(EXTRA_LANDMARK_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        startActivity(intent, options.toBundle());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Glide.get(getActivity()).clearMemory();
    }
}