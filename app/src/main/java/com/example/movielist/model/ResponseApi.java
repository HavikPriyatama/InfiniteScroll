package com.example.movielist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseApi {
    @SerializedName("page")
    int page;
    @SerializedName("total_result")
    int total_result;
    @SerializedName("total_pages")
    int total_pages;
    @SerializedName("results")
    List<Movie> result;

    public ResponseApi() {
    }

    public ResponseApi(int page, int total_result, int total_pages, List<Movie> result) {
        this.page = page;
        this.total_result = total_result;
        this.total_pages = total_pages;
        this.result = result;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_result() {
        return total_result;
    }

    public void setTotal_result(int total_result) {
        this.total_result = total_result;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getResult() {
        return result;
    }

    public void setResult(List<Movie> result) {
        this.result = result;
    }
}