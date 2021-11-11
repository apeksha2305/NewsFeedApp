package com.practice.assignment.newsfeedapp.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.practice.assignment.newsfeedapp.api.ApiClient;
import com.practice.assignment.newsfeedapp.api.ApiInterface;
import com.practice.assignment.newsfeedapp.model.NewsFeedModel;
import com.practice.assignment.newsfeedapp.model.network.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedRepository {

    public MutableLiveData<NewsResponse> getNewsHeadlines(Context context, String category, String country){
        MutableLiveData<NewsResponse> resultedData = new MutableLiveData<>();
        try {
            ApiInterface apiInterface = ApiClient.getClient(context).create(ApiInterface.class);
            Call<NewsResponse> call = apiInterface
                    .getHeadlines(category);//, country);

            call.enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    Log.d(NewsFeedRepository.class.getSimpleName(), "Response: "+response.body());
                    resultedData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {
                    Log.d(NewsFeedRepository.class.getSimpleName(), "Error: "+t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultedData;
    }

}
