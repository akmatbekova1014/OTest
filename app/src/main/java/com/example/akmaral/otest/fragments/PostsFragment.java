package com.example.akmaral.otest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.akmaral.otest.models.Post;
import com.example.akmaral.otest.interfaces.PostsApiService;
import com.example.akmaral.otest.R;
import com.example.akmaral.otest.RandomNumbers;
import com.example.akmaral.otest.adapters.PostsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostsFragment extends Fragment {
    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    ArrayList<Post> all_posts = new ArrayList<>();
    ArrayList<Post> random_posts = new ArrayList<>();
    PostsAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_posts, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.posts);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new PostsAdapter(random_posts,getContext());
        mRecyclerView.setAdapter(adapter);




        connectAndGetApiData();


        return v;
    }

    private void connectAndGetApiData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostsApiService postsApiService = retrofit.create(PostsApiService.class);
        Call<List<Post>> pos = postsApiService.getPosts();

        pos.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                all_posts.addAll(response.body());

                //Случайные числа
                ArrayList<Integer> numbers = RandomNumbers.getRandomData(all_posts.size(),15);

                //Посты для адаптера

                for (int i = 0; i < all_posts.size(); i++){
                    if (numbers.contains(all_posts.get(i).getId()))
                        random_posts.add(all_posts.get(i));
                }

                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getContext(),"Отсутствует подключение к интернету", Toast.LENGTH_LONG).show();

            }
        });
    }

}