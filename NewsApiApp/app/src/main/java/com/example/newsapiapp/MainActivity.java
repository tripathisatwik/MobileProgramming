package com.example.newsapiapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsArticle> newsList;
    private EditText searchEditText;
    private Button searchButton;

    private String apiKey = "a697efd63c894ee388d59e1486b98f30";
    private String baseUrl = "https://newsapi.org/v2/everything?q=";
    private String currentQuery = "trending";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsList = new ArrayList<>();
        newsAdapter = new NewsAdapter(newsList, this);
        newsRecyclerView.setAdapter(newsAdapter);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        fetchNews(currentQuery);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString().trim();
                if (!query.isEmpty()) {
                    currentQuery = query;
                    fetchNews(currentQuery);
                }
            }
        });
    }

    private void fetchNews(String query) {
        String url = baseUrl + query + "&apiKey=" + apiKey;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        newsList.clear();
                        try {
                            JSONArray articles = response.getJSONArray("articles");

                            for (int i = 0; i < articles.length(); i++) {
                                JSONObject articleJson = articles.getJSONObject(i);
                                JSONObject source = articleJson.getJSONObject("source");
                                NewsArticle newsArticle = new NewsArticle(
                                        articleJson.getString("title"),
                                        articleJson.getString("description"),
                                        articleJson.getString("urlToImage"),
                                        source.getString("name"),
                                        articleJson.getString("publishedAt"),
                                        articleJson.getString("url")
                                );
                                newsList.add(newsArticle);
                            }
                            newsAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Log.e("JSONException", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", error.toString());
                if (error.networkResponse != null) {
                    Log.e("VolleyError", "Status Code: " + error.networkResponse.statusCode);
                    try {
                        String responseBody = new String(error.networkResponse.data, "UTF-8");
                        Log.e("VolleyError", "Response Body: " + responseBody);
                    } catch (java.io.UnsupportedEncodingException e) {
                        Log.e("VolleyError", "Error decoding response body: " + e.toString());
                    }
                }
            }
        }) {
            @Override
            public java.util.Map<String, String> getHeaders() {
                java.util.Map<String, String> headers = new java.util.HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
                return headers;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}