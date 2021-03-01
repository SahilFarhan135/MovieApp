package com.example.printful;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModal implements Parcelable {
    /*
    "adult": false,
            "backdrop_path": "/8tNX8s3j1O0eqilOQkuroRLyOZA.jpg",
            "genre_ids": [
                14,
                28,
                12
            ],
            "id": 458576,
            "original_language": "en",
            "original_title": "Monster Hunter",
            "overview": "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
            "popularity": 3447.755,
            "poster_path": "/uwjaCH7PiWrkz7oWJ4fcL3xGrb0.jpg",
            "release_date": "2020-12-03",
            "title": "Monster Hunter",
            "video": false,
            "vote_average": 7.2,
            "vote_count": 783
     */
    private String original_title;
    private String original_language;
    private String poster_path;
    private String release_date;
    private int movie_id;
    private float vote_average;
    private String overview;

    public MovieModal(String original_title, String original_language, String poster_path, String release_date, int movie_id, float vote_average, String overview) {
        this.original_title = original_title;
        this.original_language = original_language;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.overview = overview;
    }

    public MovieModal() {
    }

    protected MovieModal(Parcel in) {
        original_title = in.readString();
        original_language = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        vote_average = in.readFloat();
        overview = in.readString();
    }

    public static final Creator<MovieModal> CREATOR = new Creator<MovieModal>() {
        @Override
        public MovieModal createFromParcel(Parcel in) {
            return new MovieModal(in);
        }

        @Override
        public MovieModal[] newArray(int size) {
            return new MovieModal[size];
        }
    };

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(original_title);
        parcel.writeString(original_language);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeInt(movie_id);
        parcel.writeFloat(vote_average);
        parcel.writeString(overview);
    }
}

