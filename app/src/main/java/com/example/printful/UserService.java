package com.example.printful;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {






    @GET("https://api.themoviedb.org/3/search/movie")
    Call<MovieListResponse> movielist(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page);

    @GET("https://api.themoviedb.org/3/movie/latest")
    Call<MovieModal> latest(
            @Query("api_key") String key);

    @GET("https://api.themoviedb.org/3/list/3")
    Call<MovieListResponse> list(
            @Query("api_key") String key);


    @GET("Https://api.themoviedb.org/3/movie/now_playing")
    Call<MovieListResponse> list(
            @Query("api_key") String key,
            @Query("page") String page);

}
