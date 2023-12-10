package com.example.Library.Management.System.Application.Entities;

import com.example.Library.Management.System.Application.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    //private Date issueDate;

    private Date returnDate;

    private Integer fine;

    @CreationTimestamp
    private Date createdOn; // Handled by Spring Internally

    @UpdateTimestamp
    private Date lastModifiedOn;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private LibraryCard card;

    public Integer getTransactionId() {
        return transactionId;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Integer getFine() {
        return fine;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public Book getBook() {
        return book;
    }

    public LibraryCard getCard() {
        return card;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setCard(LibraryCard card) {
        this.card = card;
    }
}
