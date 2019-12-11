package com.mindyapps.android.landly.ui.landmark;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.mindyapps.android.landly.util.Constants.EXTRA_LANDMARK_IMAGE_TRANSITION_NAME;
import static com.mindyapps.android.landly.util.Constants.LANDMARK_NAME_EXTRA;
import static com.mindyapps.android.landly.util.Constants.LANDMARK_URL_EXTRA;
import static com.mindyapps.android.landly.util.Constants.LIKES_EXTRA;
import static com.mindyapps.android.landly.util.Constants.USERNAME_EXTRA;
import static com.mindyapps.android.landly.util.Constants.USER_IMAGE_EXTRA;
import static com.mindyapps.android.landly.util.Constants.VIEWS_EXTRA;

public class LandmarkActivity extends DaggerAppCompatActivity {

    private TextView tvUserName, tvLikes, tvViews;
    private ImageView landmarkImage, userImage;
    private String userName, landmarkName, userImageUrl, landmarkImageUrl;
    private int likes, views;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark);
        supportPostponeEnterTransition();

        tvUserName = findViewById(R.id.user_name);
        tvLikes = findViewById(R.id.likes_count);
        tvViews = findViewById(R.id.views_count);
        landmarkImage = findViewById(R.id.landmark_image);
        userImage = findViewById(R.id.user_image);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = getIntent().getExtras()
                    .getString(EXTRA_LANDMARK_IMAGE_TRANSITION_NAME);
            landmarkImage.setTransitionName(imageTransitionName);
        }

        setExtras();
        bindView();
    }

    private void bindView() {
        setTitle(landmarkName);
        tvUserName.setText(userName);
        tvLikes.setText(String.valueOf(likes));
        tvViews.setText(String.valueOf(views));

        requestManager
                .load(userImageUrl)
                .fitCenter()
                .apply(RequestOptions.circleCropTransform())
                .error(R.color.colorPrimaryDark)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        userImage.setImageDrawable(resource);
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
        requestManager
                .load(landmarkImageUrl)
                .fitCenter()
                .error(R.color.colorPrimaryDark)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        landmarkImage.setImageDrawable(resource);
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    private void setExtras() {
        landmarkName = getIntent().getStringExtra(LANDMARK_NAME_EXTRA);
        userName = getIntent().getStringExtra(USERNAME_EXTRA);
        userImageUrl = getIntent().getStringExtra(USER_IMAGE_EXTRA);
        landmarkImageUrl = getIntent().getStringExtra(LANDMARK_URL_EXTRA);
        likes = getIntent().getIntExtra(LIKES_EXTRA, 0);
        views = getIntent().getIntExtra(VIEWS_EXTRA, 0);
    }
}
