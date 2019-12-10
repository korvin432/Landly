package com.mindyapps.android.landly.ui.search;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mindyapps.android.landly.R;
import com.mindyapps.android.landly.models.Landmark;
import com.mindyapps.android.landly.ui.random.RandomRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder> {

    private static final String TAG = "RandomRecyclerAdapter";
    private Landmark landmark;
    private List<String> imageUrls = new ArrayList<>();

    RequestManager requestManager;

    @Inject
    public SearchRecyclerAdapter(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder, int position) {
        holder.landMarkImage.setImageResource(R.drawable.loading_placeholder);
        if (requestManager != null && landmark.getHitList().size() != 0) {
            try {
                requestManager
                        .load(imageUrls.get(position))
                        .fitCenter()
                        .thumbnail(0.5f)
                        .skipMemoryCache(true)
                        .override(500,500)
                        .placeholder(R.drawable.loading_placeholder)
                        .into(new CustomTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
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
    }

    public void clear() {
        int size = imageUrls.size();
        imageUrls.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public int getItemCount() {
        return this.imageUrls.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setLandmarks(Landmark landmark) {
        this.landmark = landmark;
        imageUrls.clear();
        for (int i = 0; i < landmark.getHitList().size(); i++){
            imageUrls.add(landmark.getPreviewUrl(i));
        }
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        public ImageView landMarkImage;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            landMarkImage = itemView.findViewById(R.id.search_image);
            landMarkImage.layout(0,0,0,0);
        }
    }
}
