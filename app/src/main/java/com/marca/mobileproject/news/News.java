package com.marca.mobileproject.news;

import java.util.Date;

public class News {

    private Long date;
    private String title;
    private String description;

    public News(final Long date, final String title, final String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public News() {

    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "News{" +
                "date=" + date +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
