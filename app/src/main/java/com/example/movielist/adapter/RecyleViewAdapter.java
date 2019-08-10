package com.example.movielist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.movielist.R;
import com.example.movielist.model.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyleViewAdapter extends RecyclerView.Adapter<RecyleViewAdapter.MyViewHolder> {

    private List<Movie> movies;
    private Context context;

    public RecyleViewAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.card_movie, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int i) {
        //this is for bind data into cardview, for now this is not important
        holder.filmRating.setText("Rating : "+movies.get(i).getVote_average());
        holder.filmTittle.setText(movies.get(i).getTittle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/original"+movies.get(i).getPoster_path())
                .resize(150,200)
                .into(holder.filmImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        //todo onclick listener
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView filmImage;
        TextView filmTittle, filmRating;
        ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            filmImage = itemView.findViewById(R.id.filmImage);
            filmTittle = itemView.findViewById(R.id.filmTittle);
            filmRating = itemView.findViewById(R.id.filmRating);
            progressBar = itemView.findViewById(R.id.progress_circular);
        }
    }

    public void addMovie(List<Movie> movieList){
        for(Movie movie : movieList){
            movies.add(movie);
        }
        notifyDataSetChanged();
    }
}
