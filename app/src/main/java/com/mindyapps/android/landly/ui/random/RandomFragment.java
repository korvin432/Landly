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
import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class RandomFragment extends DaggerFragment {

    private static final String TAG = "RandomFragment";

    private RandomViewModel randomViewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        randomViewModel =
                ViewModelProviders.of(this, providerFactory).get(RandomViewModel.class);
        randomViewModel.init();

        View root = inflater.inflate(R.layout.fragment_random, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        randomViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        randomViewModel.getLandmarkRepository().observe(this, new Observer<Landmark>() {
            @Override
            public void onChanged(Landmark landmark) {
                String name = landmark.getName();
                String imageUrl = landmark.getImageUrl();
                Log.d(TAG, "onCreateView: " + name + ": " + imageUrl);
            }
        });

        return root;
    }
}