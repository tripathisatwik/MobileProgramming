package com.example.chaptersix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.txtMovieTitle.setText(movie.getTitle());
        holder.txtMovieYear.setText(String.valueOf(movie.getYear()));
        holder.txtMovieGenre.setText(movie.getGenre());
        holder.imageView.setImageResource(movie.getImages()[0]);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMovieTitle, txtMovieYear, txtMovieGenre;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMovieTitle = itemView.findViewById(R.id.rv_movie_name);
            txtMovieYear = itemView.findViewById(R.id.rv_movie_year);
            txtMovieGenre = itemView.findViewById(R.id.rv_movie_genre);
            imageView = itemView.findViewById(R.id.rv_movie_image);
        }
    }
}