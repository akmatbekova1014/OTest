package com.example.akmaral.otest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.akmaral.otest.adapters.CommentsAdapter;
import com.example.akmaral.otest.interfaces.CommentsApiService;
import com.example.akmaral.otest.models.Comments;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsActivity extends AppCompatActivity {

    int postId;

    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CommentsAdapter adapter;

    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    ArrayList<Comments> comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);


        postId = getIntent().getIntExtra("postId",0);

        Log.e("TAG", "postId"+postId);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_for_comments);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new CommentsAdapter(comments);
        mRecyclerView.setAdapter(adapter);

        connectAndGetApiData();

    }

    private void connectAndGetApiData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommentsApiService service = retrofit.create(CommentsApiService.class);
        Call<List<Comments>> com = service.getComments(postId);
        com.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                comments.addAll(response.body());
                Log.e("TAG", comments.size()+"comments.size");
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

            }
        });

    }
}
