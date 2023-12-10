package com.example.Library.Management.System.Application.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //this means its structure that will reflect in database
@Table(name = "student") // This class will have table whose name is student
@Getter // generates getters automatically
@Setter // generates Setters automatically
@AllArgsConstructor //generates Constructors automatically with all arguments as input param
@NoArgsConstructor //generates Constructors automatically with no argument as input param
public class Student {

    @Id //this writes on the primary key
    private  Integer studentId; // this will behave as Primary Key

    private String name;

    private int age;

    @Column(name = "contactNo", unique = true, nullable = false)
    private String mobNo;

    private String emailId;

    private String bloodGroup;

    @JsonIgnore
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL) // with cascade it will auto save the child table by saving the parent table.
    private LibraryCard libraryCard;                            // cascade can only happen if we done Bi-directional mapping


    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }
}
