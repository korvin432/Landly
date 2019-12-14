package com.mindyapps.android.landly.ui.favourite;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.db.LandmarkEntity;
import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.ui.random.OnLandmarkListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavouriteRecyclerAdapter extends RecyclerView.Adapter<FavouriteRecyclerAdapter.FavouriteViewHolder> {
    private static final String TAG = "RandomRecyclerAdapter";
    private List<LandmarkEntity> landmarks = new ArrayList<>();

    RequestManager requestManager;

    private OnLandmarkListener onLandmarkListener;

    @Inject
    public FavouriteRecyclerAdapter(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_landmarks_list_item, parent, false);
        return new FavouriteViewHolder(view, onLandmarkListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavouriteViewHolder holder, int position) {
        LandmarkEntity landmark = landmarks.get(position);
        if (landmark != null && landmark.getImageUrl() != null) {

            holder.landMarkImage.setImageResource(R.drawable.loading_placeholder);
            holder.title.setText(landmark.getLandmarkName());
            try {
                requestManager
                        .load(landmark.getImageUrl())
                        .fitCenter()
                        .error(R.color.colorPrimaryDark)
                        .into(new CustomTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                holder.landMarkImage.setImageDrawable(resource);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {

                            }
                        });

                ViewCompat.setTransitionName(holder.landMarkImage, landmark.getLandmarkName());
            } catch (Exception ex) {
                Log.d(TAG, "onBindViewHolder: " + ex.getMessage());
            }

        }
    }

    public void setOnItemClickListener(OnLandmarkListener onItemClickListener) {
        this.onLandmarkListener = onItemClickListener;
    }

    @Override
    public void onViewRecycled(@NonNull FavouriteViewHolder holder) {
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

    public void setLandmarks(List<LandmarkEntity> landmarks) {
        this.landmarks = landmarks;
        notifyDataSetChanged();
    }

    public LandmarkEntity getSelectedLandmark(int position) {
        if (landmarks != null) {
            if (landmarks.size() > 0) {
                return landmarks.get(position);
            }
        }
        return null;
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public ImageView landMarkImage;
        OnLandmarkListener onLandmarkListener;

        public FavouriteViewHolder(@NonNull View itemView, OnLandmarkListener onLandmarkListener) {
            super(itemView);
            this.onLandmarkListener = onLandmarkListener;

            title = itemView.findViewById(R.id.tv_name);
            landMarkImage = itemView.findViewById(R.id.landmark_image);
            landMarkImage.layout(0, 0, 0, 0);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onLandmarkListener.onLandmarkClick(getAdapterPosition(), landMarkImage);
        }
    }
}
