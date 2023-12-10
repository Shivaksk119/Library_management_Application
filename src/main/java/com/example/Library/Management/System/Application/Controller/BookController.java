package com.example.Library.Management.System.Application.Controller;

import com.example.Library.Management.System.Application.DTOs.AddBookRequest;
import com.example.Library.Management.System.Application.Entities.Book;
import com.example.Library.Management.System.Application.Repository.BookRepository;
import com.example.Library.Management.System.Application.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBookViaDto")
    public ResponseEntity addBookViaDto(@RequestBody AddBookRequest bookRequest) {
        try {
            String result = bookService.addBook(bookRequest);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book, @RequestParam("authorId") Integer authorId) {
        try {
             String result = bookService.addBook(book, authorId);
             return new ResponseEntity(result, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
