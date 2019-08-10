package com.example.movielist.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("id")
    int id;
    @SerializedName("vote_average")
    String vote_average;
    @SerializedName("title")
    String tittle;
    @SerializedName("poster_path")
    String poster_path;
    @SerializedName("overview")
    String overview;
    @SerializedName("release_date")
    String release_date;

    public Movie() {
    }

    public Movie(int id, String vote_average, String tittle, String poster_path, String overview, String release_date) {
        this.id = id;
        this.vote_average = vote_average;
        this.tittle = tittle;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
