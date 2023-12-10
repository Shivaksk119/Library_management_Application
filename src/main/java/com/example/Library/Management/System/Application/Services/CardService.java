package com.example.Library.Management.System.Application.Services;


import com.example.Library.Management.System.Application.Entities.LibraryCard;
import com.example.Library.Management.System.Application.Entities.Student;
import com.example.Library.Management.System.Application.Enums.CardStatus;
import com.example.Library.Management.System.Application.Repository.CardRepository;
import com.example.Library.Management.System.Application.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public LibraryCard generateCard() {

        LibraryCard card = new LibraryCard();

        card.setCardStatus(CardStatus.NEW);

        card.setNoOfBooksIssued(0);

        card = cardRepository.save(card);

        return card;
    }

    public String associateStudentWithCard(Integer studentId, Integer cardNo) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Student student = studentOptional.get();

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardNo);
        LibraryCard libraryCard = optionalLibraryCard.get();

        //Setting the required attributes of LibraryCard
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setNameOnCard(student.getName());
        libraryCard.setStudent(student);

        //Setting the required attributes of Student
        student.setLibraryCard(libraryCard);

        studentRepository.save(student);

        return "Card with cardNo "+cardNo+" has been asociated to "+studentId;

    }
}
