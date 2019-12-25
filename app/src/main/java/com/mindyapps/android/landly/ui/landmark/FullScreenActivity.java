package com.mindyapps.android.landly.ui.landmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.mindyapps.android.landly.R;

import static com.mindyapps.android.landly.util.Constants.IMAGE_URL_EXTRA;

public class FullScreenActivity extends AppCompatActivity {

    PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_full_screen);

        photoView = findViewById(R.id.photo_view);

        String imageUrl = getIntent().getStringExtra(IMAGE_URL_EXTRA);
        Glide.with(this).load(imageUrl).into(photoView);
    }
}
