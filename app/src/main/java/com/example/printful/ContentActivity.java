package com.example.printful;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {

    AppCompatImageView Poster;
    RatingBar rating;
    MaterialTextView title;
    Button back;
    TextView releaseDate,Lang,descirption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Poster=findViewById(R.id.poster);
        rating=findViewById(R.id.rating);
        title=findViewById(R.id.movie_title);
        releaseDate=findViewById(R.id.release_date);
        Lang=findViewById(R.id.lan);
        descirption=findViewById(R.id.tv_overview);
        back=findViewById(R.id.back);





        String Movie_title=  getIntent().getStringExtra("movie_title");
        String Description=getIntent().getStringExtra("description");
        String lan= getIntent().getStringExtra("lan");
        String release_date= getIntent().getStringExtra("release_date");
        String poster=getIntent().getStringExtra("poster");
        String id=  getIntent().getStringExtra("id");
        String Rate=getIntent().getStringExtra("rating");


        if(lan.equals("en")){
            lan="English";
        }
        title.setText(Movie_title);
        descirption.setText(Description);
        Lang.setText(lan);
        releaseDate.setText(release_date);
        rating.setRating(Float.parseFloat(Rate)/2);
        Toast.makeText(ContentActivity.this,Rate,Toast.LENGTH_LONG).show();

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+poster)
                 .fitCenter()
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .error(android.R.drawable.stat_notify_error)
                .into(Poster);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

    }
}