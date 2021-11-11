package com.practice.assignment.newsfeedapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.practice.assignment.newsfeedapp.R;
import com.practice.assignment.newsfeedapp.adapter.NewsRecyclerViewAdapter;
import com.practice.assignment.newsfeedapp.databinding.ActivityMainBinding;
import com.practice.assignment.newsfeedapp.model.network.NewsArticle;
import com.practice.assignment.newsfeedapp.modelview.MainViewModel;

import java.util.List;

import swipeable.com.layoutmanager.OnItemSwiped;
import swipeable.com.layoutmanager.SwipeableLayoutManager;
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback;
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private NewsRecyclerViewAdapter adapter;
    private List<NewsArticle> newsArticlesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        loadData();
    }

    private void loadData() {
        viewModel.newsArticleList.observeForever(new Observer<List<NewsArticle>>() {
            @Override
            public void onChanged(List<NewsArticle> newsArticles) {
                if (binding.swipeRefreshLayout.isRefreshing())
                    binding.swipeRefreshLayout.setRefreshing(false);
                if (newsArticlesList != null && newsArticlesList.size() > 0)
                    newsArticlesList.clear();

                newsArticlesList = newsArticles;

                if (adapter != null){
//                    adapter.notifyDataSetChanged();
                    createRecyclerViewAdapter();
                }else {
                    createRecyclerViewAdapter();
                }
            }
        });

        viewModel.getLatestNews(this);

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getLatestNews(MainActivity.this);
            }
        });
    }

    private void createRecyclerViewAdapter(){
        adapter = new NewsRecyclerViewAdapter(newsArticlesList, MainActivity.this);
        SwipeableLayoutManager swipeableLayoutManager = new SwipeableLayoutManager()
                .setAngle(10)
                .setAnimationDuratuion(500)
                .setMaxShowCount(3)
                .setScaleGap(0.1f)
                .setTransYGap(0);

        binding.rvNewsFeed.setLayoutManager(swipeableLayoutManager);

        binding.rvNewsFeed.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeableTouchHelperCallback(new OnItemSwiped() {
            @Override
            public void onItemSwiped() {
                adapter.removeTopItem();
            }

            @Override
            public void onItemSwipedLeft() {

            }

            @Override
            public void onItemSwipedRight() {

            }

            @Override
            public void onItemSwipedUp() {

            }

            @Override
            public void onItemSwipedDown() {

            }
        }));
        itemTouchHelper.attachToRecyclerView(binding.rvNewsFeed);
    }
}
