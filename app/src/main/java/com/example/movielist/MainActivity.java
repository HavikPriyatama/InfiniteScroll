package com.example.movielist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movielist.adapter.RecyleViewAdapter;
import com.example.movielist.api.ImdbApi;
import com.example.movielist.model.Movie;
import com.example.movielist.model.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int page = 1;
    private ImdbApi api;

    private RecyleViewAdapter adapter;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private RecyleViewAdapter recyleViewAdapter;

    private boolean isLoading = true;
    private int pastVisibleItem, visibleItemCount, totalItemCount, previousTotal = 0;
    private int viewThreshold = 20;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycleview);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ImdbApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ImdbApi.class);
        Call<ResponseApi> call = api.getResponse(ImdbApi.KEY,page);
        call.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                List<Movie> movies = response.body().getResult();

                Log.d(TAG, "Response : " + response.toString());

                recyclerView = findViewById(R.id.recycleview);
                adapter = new RecyleViewAdapter(movies, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if(dy > 0){
                    if(isLoading){
                        if(totalItemCount > previousTotal){
                            isLoading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if(!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItem + viewThreshold)){
                        page++;
                        performPagination();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void performPagination(){
        progressBar.setVisibility(View.VISIBLE);
        Call<ResponseApi> call = api.getResponse(ImdbApi.KEY,page);
        call.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                List<Movie> movies = response.body().getResult();
                Log.d(TAG, "Response : " + response.toString());
                if(movies.size() == 0){
                    Toast.makeText(getApplicationContext(),"No More Data To Load",Toast.LENGTH_SHORT).show();
                }else{
                    adapter.addMovie(movies);
                    Toast.makeText(getApplicationContext(),"Here we go",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {

            }
        });
    }
}
