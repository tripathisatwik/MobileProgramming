package com.example.newsapiapp;

public class NewsArticle {
    private String title;
    private String description;
    private String urlToImage;
    private String sourceName;
    private String publishedAt;
    private String url; // Add this field

    public NewsArticle(String title, String description, String urlToImage, String sourceName, String publishedAt, String url) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.sourceName = sourceName;
        this.publishedAt = publishedAt;
        this.url = url;
    }

    // Getters for all fields...

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getUrl() {
        return url;
    }
}