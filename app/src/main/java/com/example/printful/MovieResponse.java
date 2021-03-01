package com.example.printful;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

   @SerializedName("results")
   @Expose
    private MovieModal movie;

    public MovieModal getMovie() {
        return movie;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieResponse{"
                + "movie = "+movie+"}";
    }




}
