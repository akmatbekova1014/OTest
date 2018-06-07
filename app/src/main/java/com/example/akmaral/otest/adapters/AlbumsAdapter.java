package com.example.akmaral.otest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akmaral.otest.PhotosActivity;
import com.example.akmaral.otest.models.Album;
import com.example.akmaral.otest.R;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder>{


    Context context;

    List<Album> albums;

    public AlbumsAdapter(List<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.ViewHolder holder, int position) {

        final Album album = albums.get(position);
        holder.album_title.setText(album.getTitle());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotosActivity.class);
                intent.putExtra("albumId", album.getId());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        if (albums == null)
            return 0;
        return albums.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView album_title;
        public View container;

        public ViewHolder(View v) {
            super(v);
            album_title = (TextView) v.findViewById(R.id.album_title);
            container = v;
        }

        @Override
        public void onClick(View v) {

        }
    }
}



