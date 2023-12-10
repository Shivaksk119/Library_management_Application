package com.example.Library.Management.System.Application.Entities;

import com.example.Library.Management.System.Application.Enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library_card")
@AllArgsConstructor //generates Constructors automatically with all arguments as input param
@NoArgsConstructor //generates Constructors automatically with no argument as input param
@Getter // generates getters automatically
@Setter // generates Setters automatically
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private String nameOnCard;

    private Integer noOfBooksIssued;

    //Library Card needs to be connected with the student table

    @OneToOne
    @JsonIgnore
    @JoinColumn // adds new column to table
    private Student student; /// writing the foreign in the child class

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();

    public Integer getNoOfBooksIssued() {
        if(noOfBooksIssued==null) {
            return 0;
        }
        return noOfBooksIssued;
    }

    public void setNoOfBooksIssued(Integer noOfBooksIssued) {
        this.noOfBooksIssued = noOfBooksIssued;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
