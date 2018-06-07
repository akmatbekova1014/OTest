package com.example.akmaral.otest.interfaces;

import com.example.akmaral.otest.models.Album;
import com.example.akmaral.otest.models.Photos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotosApiService {
    @GET("photos")
    Call<List<Photos>> getPhotos();
}
