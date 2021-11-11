package com.practice.assignment.newsfeedapp.api;

import com.practice.assignment.newsfeedapp.model.network.NewsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    String API_KEY = "4ebaeccb339c458e94e341fc0ebbc77c";//BuildConfig.NewsApiKey;

    @Headers("X-Api-Key:" + API_KEY)
    @GET("/v2/top-headlines")
    Call<NewsResponse> getHeadlines(
            @Query("category") String category//,
            //@Query("country") String country
    );
}
