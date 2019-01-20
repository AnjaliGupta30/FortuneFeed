package com.example.asus.fortunefeed.network;

import com.example.asus.fortunefeed.model.Articles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ASUS on 12-01-2019.
 */

public interface GetDataService {

    @GET("top-headlines?sources=google-news&apiKey=a5ff7ad343e941cdb86cc6d7931d24ec")
    Call<Articles> getArticlesData();
}
