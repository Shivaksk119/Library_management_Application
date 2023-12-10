package com.example.Library.Management.System.Application.Controller;

import com.example.Library.Management.System.Application.Entities.LibraryCard;
import com.example.Library.Management.System.Application.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/generatePlainCard")
    public ResponseEntity generatePlainCard() {

        LibraryCard newCard = cardService.generateCard();

        String response = "The new card has been generated and having a cardNo "+newCard.getCardNo();

        return new ResponseEntity(response, HttpStatus.OK);

    }

    @PutMapping("/associateWithStudent")
    public ResponseEntity associateWithStudent(@RequestParam("studentId")Integer studentId, @RequestParam("cardNo")Integer cardNo) {

        String response = cardService.associateStudentWithCard(studentId, cardNo);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
