package com.example.printful.homescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.printful.ApiClient;
import com.example.printful.ContentActivity;
import com.example.printful.Credential;
import com.example.printful.MovieListResponse;
import com.example.printful.MovieModal;
import com.example.printful.MovieResponse;
import com.example.printful.R;
import com.example.printful.UserService;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity {


    Button refresh,next,prev;
    RecyclerView recyclerView,recyclerView1;
    AdapterList adapterList;
    LinearLayoutManager linearLayoutManager;
    ShimmerFrameLayout shimmerFrameLayout;
    int page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recyclerView =findViewById(R.id.recyclerview);
        refresh=findViewById(R.id.nextBtn);
        next=findViewById(R.id.btnext);
        prev=findViewById(R.id.btprev);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        page=1;

        getRetrofitRespone("1");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getRetrofitRespone("1");
                Toast.makeText(HomeScreen.this,"Click refresh button to refresh",Toast.LENGTH_LONG).show();
            }
        },9000);


     next.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             page++;
             getRetrofitRespone(String.valueOf(page));
             Toast.makeText(HomeScreen.this," Please wait while Loading",Toast.LENGTH_LONG).show();

         }
     });

         prev.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(page==1){
                     Toast.makeText(HomeScreen.this,"Next to get more",Toast.LENGTH_SHORT).show();
                 }else {
                     --page;
                     getRetrofitRespone(String.valueOf(page));
                     Toast.makeText(HomeScreen.this," Please wait while Loading",Toast.LENGTH_LONG).show();

                 }
             }
         });






        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRetrofitRespone("1");
                Toast.makeText(HomeScreen.this,"Loading Please wait",Toast.LENGTH_LONG).show();
            }
        });





    }



    private void getRetrofitRespone(String page) {


        Call<MovieListResponse> movieList = ApiClient.getUserService().list(
                Credential.API_KEY
                ,
                page
                );

        movieList.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if(response.code()==200){

                    shimmerFrameLayout.setVisibility(View.GONE);
                    shimmerFrameLayout.stopShimmer();
                    Log.v("tag","response"+response.body().toString());
                    List<MovieModal> list= new ArrayList<>(response.body().getMovies());
                     adapterList=new AdapterList(list,HomeScreen.this);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(HomeScreen.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapterList);

                    adapterList.setOnItemClickListener(new AdapterList.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                        }

                        @Override
                        public void onDeleteClick(int position) {

                        }
                    });

                    for (MovieModal movieModal:list){
                        Log.v("tag","Response " +"--"+movieModal.getOriginal_title());
                    }

                }else
                {
                    //Toast.makeText(HomeScreen.this,"Click refresh button to refresh",Toast.LENGTH_LONG).show();
                    Toast.makeText(HomeScreen.this,"Click refresh button to refresh or check network connectivity",Toast.LENGTH_LONG).show();

                    Log.v("tag","Release date"+response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Toast.makeText(HomeScreen.this,"Click refresh button to refresh or check network connectivity",Toast.LENGTH_LONG).show();

            }
        });




    }
}