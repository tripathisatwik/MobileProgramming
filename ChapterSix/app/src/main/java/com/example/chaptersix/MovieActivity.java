package com.example.chaptersix;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerMovieView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Movie> movieList = createMovieList();
        MovieAdapter adapter = new MovieAdapter(movieList);
        recyclerView.setAdapter(adapter);
    }

    private List<Movie> createMovieList() {
        List<Movie> movieList = new ArrayList<>();
        int[] images1 = {R.drawable.ic_launcher_background};
        movieList.add(new Movie("Movie 1", "Action", 2023, images1));

        int[] images2 = {R.drawable.ic_launcher_background};
        movieList.add(new Movie("Movie 2", "Comedy", 2022, images2));

        int[] images3 = {R.drawable.ic_launcher_background};
        movieList.add(new Movie("Movie 3", "Romantic", 2025, images3));

        return movieList;
    }
}