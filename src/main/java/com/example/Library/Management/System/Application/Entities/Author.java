package com.example.Library.Management.System.Application.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    @Column(nullable = false)
    private String authorName;

    private int age;

    private double rating;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

    public Author() {
    }

    public Author(String authorName, int age, double rating) {
        this.authorName = authorName;
        this.age = age;
        this.rating = rating;
    }

    public Author(Integer authorId, String authorName, int age, double rating) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.age = age;
        this.rating = rating;
    }

    public Author(Integer authorId, String authorName, int age, double rating, List<Book> bookList) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.age = age;
        this.rating = rating;
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
