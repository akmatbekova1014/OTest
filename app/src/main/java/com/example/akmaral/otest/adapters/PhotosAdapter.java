package com.example.akmaral.otest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.akmaral.otest.ImageActivity;
import com.example.akmaral.otest.R;
import com.example.akmaral.otest.models.Photos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {


    private ArrayList<Photos> photos = new ArrayList<>();
    private Context context;

    public PhotosAdapter(ArrayList<Photos> photos, Context context) {
        this.photos = photos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.photos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Photos photo = photos.get(position);

        Log.e("TAG", photo.getUrl());
        Picasso.with(context).load(photo.getUrl()).into(holder.imageView);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageActivity.class);
                intent.putExtra("photo_url", photo.getUrl());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        public View container;

        public ViewHolder(View itemView) {
            super(itemView);
            container = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.photo_item);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
