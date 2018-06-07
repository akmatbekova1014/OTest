package com.example.akmaral.otest.interfaces;

import com.example.akmaral.otest.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsApiService {
    @GET("albums")
    Call<List<Post>> getPosts();
}
