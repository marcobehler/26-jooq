package com.marcobehler.springbootexercise;

import java.time.LocalDate;

public class Article {

    private Integer id;

    private String author;

    private LocalDate publicationDate;

    private String title;

    private String headline;

    public Article() {
    }

    public Article(Integer id, String author, LocalDate publicationDate, String title, String headline) {
        this.id = id;
        this.author = author;
        this.publicationDate = publicationDate;
        this.title = title;
        this.headline = headline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
