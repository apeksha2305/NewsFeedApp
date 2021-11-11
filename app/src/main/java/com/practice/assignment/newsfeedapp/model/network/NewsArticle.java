package com.practice.assignment.newsfeedapp.model.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class NewsArticle {
    @Expose(serialize = false, deserialize = false)
    public int id;
    @SerializedName("author")
    private  String author;
    @SerializedName("title")
    private  String title;
    @SerializedName("description")
    private  String description;
    @SerializedName("url")
    private  String url;
    @SerializedName("published_at")
    private Timestamp publishedAt;
    @SerializedName("image_url")
    private  String urlToImage;
//    @Embedded(prefix = "source_")
    private  NewsSource source;
    @SerializedName("content")
    private  String content;
    @SerializedName("category")
    @Expose(serialize = false, deserialize = false)
    private String category;
    @SerializedName("save_date")
    @Expose(serialize = false, deserialize = false)
    private Timestamp saveDate = new Timestamp(System.currentTimeMillis());

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public NewsSource getSource() {
        return source;
    }

    public void setSource(NewsSource source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Timestamp saveDate) {
        this.saveDate = saveDate;
    }
}
