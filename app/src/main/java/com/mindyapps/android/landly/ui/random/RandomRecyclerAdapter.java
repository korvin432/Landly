package com.mindyapps.android.landly.ui.random;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.di.main.MainScope;
import com.mindyapps.android.landly.models.Landmark;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RandomRecyclerAdapter extends RecyclerView.Adapter<RandomRecyclerAdapter.RandomViewHolder> {
    private static final String TAG = "RandomRecyclerAdapter";
    private List<Landmark> landmarks = new ArrayList<>();

    RequestManager requestManager;

    @Inject
    public RandomRecyclerAdapter(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public RandomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_landmarks_list_item, parent, false);
        return new RandomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RandomViewHolder holder, int position) {
        Landmark landmark = landmarks.get(position);

        if (requestManager != null && landmark.getHitList().size() != 0) {
            holder.title.setText(landmark.getName());
            try {
                requestManager
                        .load(landmark.getImageUrl(0))
                        .fitCenter()
                        .into(new CustomTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                holder.landMarkImage.setImageDrawable(null);
                                holder.progressBar.setVisibility(View.GONE);
                                holder.landMarkImage.setImageDrawable(resource);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {

                            }
                        });
            } catch (Exception ex){
                Log.d(TAG, "onBindViewHolder: " + ex.getMessage());
            }
        } else {
            return;
        }

        //((RandomViewHolder) holder).bind(landmarks.get(position));
    }

    @Override
    public void onViewRecycled(@NonNull RandomViewHolder holder) {
        requestManager.clear(holder.landMarkImage);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return landmarks.size();
    }

    public void clear() {
        int size = landmarks.size();
        landmarks.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void setLandmarks(List<Landmark> landmarks) {
        this.landmarks = landmarks;
        notifyDataSetChanged();
    }

    public class RandomViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView landMarkImage;
        public ProgressBar progressBar;

        public RandomViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_name);
            landMarkImage = itemView.findViewById(R.id.landmark_image);
            landMarkImage.layout(0,0,0,0);
            progressBar = itemView.findViewById(R.id.progress);
        }

        public void bind(Landmark landmark) {
            title.setText(landmark.getName());
            requestManager
                    .load(landmark.getImageUrl(0))
                    .into(landMarkImage);
        }
    }
}
