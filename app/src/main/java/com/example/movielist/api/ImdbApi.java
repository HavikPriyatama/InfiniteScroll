package com.example.movielist.api;

import com.example.movielist.model.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImdbApi {
    String BASE_URL = "https://api.themoviedb.org/3/";
    String KEY = "eb5525feca9a591d12dc41e418cd8021";

    @GET("movie/popular")
    Call<ResponseApi> getResponse(@Query("api_key") String apiKey, @Query("page") int page);
}