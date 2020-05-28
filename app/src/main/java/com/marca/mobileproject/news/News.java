package com.marca.mobileproject.news;

import org.jetbrains.annotations.NotNull;

public class News {

    private Long date;
    private String title;
    private String description;

    /**
     * No-argument constructor needed for Firebase.
     */
    public News() {

    }

    @SuppressWarnings("WeakerAccess")
    public Long getDate() {
        return date;
    }

    @SuppressWarnings("unused")
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

    @NotNull
    @Override
    public String toString() {
        return "News{" +
                "date=" + date +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
