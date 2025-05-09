package com.example.newsapp;

public class NewsData {
    private int image;
    private String title;
    private String description;
    private String date;

    public NewsData(int image, String title, String description, String date) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
