package com.example.akmaral.otest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.akmaral.otest.adapters.PhotosAdapter;
import com.example.akmaral.otest.interfaces.PhotosApiService;
import com.example.akmaral.otest.models.Photos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    ArrayList<Photos> all_photos = new ArrayList<>();
    ArrayList<Photos> selected_album_photos = new ArrayList<>();
    int intValue;
    PhotosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        Intent mIntent = getIntent();
        intValue = mIntent.getIntExtra("albumId", 0);
        Log.e("TAG", String.valueOf(intValue));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_for_photos);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


         adapter = new PhotosAdapter(selected_album_photos,this);
        mRecyclerView.setAdapter(adapter);

        connectAndGetApiData();
    }

    private void connectAndGetApiData() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotosApiService photosApiService = retrofit.create(PhotosApiService.class);
        Call<List<Photos>> ph = photosApiService.getPhotos();
        ph.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                all_photos.addAll(response.body());

                for (int i = 0; i < all_photos.size(); i++){
                    if (all_photos.get(i).getAlbumId() == intValue)
                        selected_album_photos.add(all_photos.get(i));
                }
                Log.e("TAG",selected_album_photos.size()+"selected_album_photos.size");
                Log.e("TAG",all_photos.size()+"all_photos.size");


                mRecyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

                

            }
        });
    }

}
