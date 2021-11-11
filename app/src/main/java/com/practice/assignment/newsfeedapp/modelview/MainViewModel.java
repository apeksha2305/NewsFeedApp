package com.practice.assignment.newsfeedapp.modelview;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.practice.assignment.newsfeedapp.model.network.NewsArticle;
import com.practice.assignment.newsfeedapp.model.network.NewsResponse;
import com.practice.assignment.newsfeedapp.repository.NewsFeedRepository;
import com.practice.assignment.newsfeedapp.utils.Constants;

import java.util.List;

public class MainViewModel extends ViewModel {
    public MutableLiveData<List<NewsArticle>> newsArticleList = new MutableLiveData<>();
    private NewsFeedRepository newsFeedRepository = new NewsFeedRepository();

    public void getLatestNews(Context context){
        LiveData<NewsResponse> response = newsFeedRepository
                .getNewsHeadlines(context, Constants.NewsCategories.general, "us");

        response.observeForever(new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                newsArticleList.setValue(newsResponse.getArticles());
                response.removeObserver(this);
            }
        });
    }
}
