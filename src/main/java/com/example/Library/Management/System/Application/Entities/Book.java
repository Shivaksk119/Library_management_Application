package com.example.Library.Management.System.Application.Entities;

import com.example.Library.Management.System.Application.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Getter // generates getters automatically
@Setter // generates Setters automatically
@AllArgsConstructor //generates Constructors automatically with all arguments as input param
@NoArgsConstructor //generates Constructors automatically with no argument as input param
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String bookName;

    private int price;

    private int noOfPages;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private double rating;

    private boolean availabilityOfBook;

    @ManyToOne //join type in acc to book
    @JoinColumn // add extra column to the table
    private Author author; // adds the foreign key

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();

    public boolean isAvailabilityOfBook() {
        return availabilityOfBook;
    }

    public void setAvailabilityOfBook(boolean availabilityOfBook) {
        this.availabilityOfBook = availabilityOfBook;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
