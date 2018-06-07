package com.example.akmaral.otest.interfaces;

import com.example.akmaral.otest.models.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumApiService {
    @GET("albums")
    Call<List<Album>> getData();
}
