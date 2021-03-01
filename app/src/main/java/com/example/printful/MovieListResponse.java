package com.example.printful;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponse {
    @SerializedName("total_results")
    @Expose
    private int total_count;

    @SerializedName("results")
    @Expose
    private List<MovieModal> movies;

    public int getTotal_count() {
        return total_count;
    }
     public List<MovieModal> getMovies(){
        return movies;
     }

    @Override
    public String toString() {
        return "MovieListResponse{" +
                "total_count=" + total_count +
                ", movies=" + movies +
                '}';
    }
}
