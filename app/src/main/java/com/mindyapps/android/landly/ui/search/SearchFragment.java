package com.mindyapps.android.landly.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.ui.random.RandomRecyclerAdapter;
import com.mindyapps.android.landly.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class SearchFragment extends DaggerFragment implements View.OnKeyListener {

    private static final String TAG = "SearchFragment";
    private SearchViewModel searchViewModel;
    private EditText etSearch;
    private RecyclerView recyclerView;

    @Inject
    SearchRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = ViewModelProviders.of(this, providerFactory)
                .get(SearchViewModel.class);
        searchViewModel.init();
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = root.findViewById(R.id.search_recycler);
        initRecyclerView();

        etSearch = root.findViewById(R.id.et_search);
        etSearch.setOnKeyListener(this);

        return root;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(
                getContext(), 3));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            searchViewModel.getData(etSearch.getText().toString()).observe(this, new Observer<Landmark>() {
                @Override
                public void onChanged(Landmark landmark) {
                    adapter.setLandmarks(landmark);
                    Log.d(TAG, "getItemCount: " + adapter.getItemCount());
                }
            });
            return true;
        }
        return false;
    }
}