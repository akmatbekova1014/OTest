package com.example.akmaral.otest.interfaces;

import com.example.akmaral.otest.models.Album;
import com.example.akmaral.otest.models.Comments;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CommentsApiService {
    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int id);
}
