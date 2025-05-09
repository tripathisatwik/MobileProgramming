package com.example.newsapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NewsMainActivity extends AppCompatActivity {

    private NewsDataAdapter adapter;
    private List<NewsData> newsList;
    private List<NewsData> filteredNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTodos);
        EditText searchBar = findViewById(R.id.searchBar);

        newsList = new ArrayList<>();
        filteredNewsList = new ArrayList<>();

        newsList.add(new NewsData(R.mipmap.ic_launcher, "Breaking News", "Description goes here", "2025-05-07"));
        newsList.add(new NewsData(R.mipmap.ic_launcher, "Tech News", "Latest tech updates", "2025-05-06"));
        newsList.add(new NewsData(R.mipmap.ic_launcher, "Sports Update", "Latest sports news", "2025-05-05"));
        newsList.add(new NewsData(R.mipmap.ic_launcher, "Politics", "Latest political news", "2025-05-04"));

        filteredNewsList.addAll(newsList);

        adapter = new NewsDataAdapter(filteredNewsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterNews(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void filterNews(String query) {
        filteredNewsList.clear();
        for (NewsData news : newsList) {
            if (news.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredNewsList.add(news);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
