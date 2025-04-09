package com.example.chaptersix;

public class Movie {
    private String title;
    private String genre;
    private int year;
    int[] images;

    public Movie(String title, String genre, int year, int[] images) { // Corrected the constructor to accept int[]
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int[] getImages() {
        return images;
    }
}