package com.example.akmaral.otest.fragments;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.akmaral.otest.models.Album;
import com.example.akmaral.otest.interfaces.AlbumApiService;
import com.example.akmaral.otest.R;
import com.example.akmaral.otest.RandomNumbers;
import com.example.akmaral.otest.adapters.AlbumsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumsFragment extends Fragment {


    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private static Retrofit retrofit = null;
    public static final String BASE_URL="https://jsonplaceholder.typicode.com/";
    ArrayList<Album> all_albums = new ArrayList<>();
    ArrayList<Album> random_albums = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_albums, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_for_albums);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        AlbumsAdapter adapter = new AlbumsAdapter(random_albums,getContext());
        mRecyclerView.setAdapter(adapter);

        connectAndGetApiData();

        return v;


    }

    private void connectAndGetApiData() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        AlbumApiService albumApiService = retrofit.create(AlbumApiService.class);

        Call<List<Album>> alb = albumApiService.getData();

        alb.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                Log.e("response " , String.valueOf(+ response.body().size()));
                all_albums.addAll(response.body());
                ArrayList<Integer> numbers = RandomNumbers.getRandomData(all_albums.size(),10);

                for (int i = 0; i < all_albums.size(); i++){
                    if (numbers.contains(all_albums.get(i).getId()))
                        random_albums.add(all_albums.get(i));
                }
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Toast.makeText(getContext(),"Отсутствует подключение к интернету", Toast.LENGTH_LONG).show();

            }
        });

    }

}
