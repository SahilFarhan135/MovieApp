package com.example.printful.homescreen;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.printful.ContentActivity;
import com.example.printful.MovieListResponse;
import com.example.printful.MovieModal;
import com.example.printful.MovieResponse;
import com.example.printful.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class AdapterList extends RecyclerView.Adapter<AdapterList.Holder>{
    List<MovieModal> arr;
    Context context;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public AdapterList(List<MovieModal> arr,Context context) {
        this.arr = arr;
        this.context = context;
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView movie_title,release,lan;
        AppCompatImageView movie_poster;
        AppCompatRatingBar rating_movie;
        ConstraintLayout layout;

        public Holder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            movie_title=itemView.findViewById(R.id.movie_title);
            release=itemView.findViewById(R.id.release_date);
            lan=itemView.findViewById(R.id.lan);
            movie_poster=itemView.findViewById(R.id.poster);
            rating_movie= itemView.findViewById(R.id.rating);
            layout=itemView.findViewById(R.id.layoutItem);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                            MovieModal movieResponse=arr.get(position);
                            Log.v("tag","ress"+movieResponse.getOriginal_title());

                           Intent intent = new Intent(context, ContentActivity.class);
                            intent.putExtra("movie_title",movieResponse.getOriginal_title());
                            intent.putExtra("description",movieResponse.getOverview());
                            intent.putExtra("lan",movieResponse.getOriginal_language());
                            intent.putExtra("release_date",movieResponse.getRelease_date());
                            intent.putExtra("poster",movieResponse.getPoster_path());
                            intent.putExtra("id",movieResponse.getMovie_id());
                            intent.putExtra("rating",String.valueOf(movieResponse.getVote_average()));
                          //  Toast.makeText(context, ""+movieResponse.getVote_average(),Toast.LENGTH_LONG).show();
                            context.startActivity(intent);
                        }
                    }
                }
            });


        }


    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie, parent,false);
        return new Holder(view,mListener) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        MovieModal movieResponse=arr.get(position);
        holder.movie_title.setText(movieResponse.getOriginal_title());
        holder.release.setText(movieResponse.getRelease_date());
        if(movieResponse.getOriginal_language().equals("ru")){
            holder.lan.setText("Russian");
        }else if(movieResponse.getOriginal_language().equals("en")){
            holder.lan.setText("English");
        }
        holder.lan.setText(movieResponse.getOriginal_language());
        holder.rating_movie.setRating(movieResponse.getVote_average()/2.5f);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+movieResponse.getPoster_path())
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.movie_poster);





    }



    @Override
    public int getItemCount() {
        return arr.size();
    }
}
