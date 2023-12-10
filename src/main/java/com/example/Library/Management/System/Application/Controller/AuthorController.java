package com.example.Library.Management.System.Application.Controller;

import com.example.Library.Management.System.Application.Entities.Author;
import com.example.Library.Management.System.Application.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {

        String result = authorService.addAuthor(author);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/findAllAuthorNames")
    public List<String> getAllAuthorNames() {
        return authorService.getAllAuthorNames();
    }

    @GetMapping("/getAuthor/{id}")
    public ResponseEntity getAuthor(@PathVariable("id")Integer id) { //ResponseEntity without mentioning data type can return any data type

        try{
            Author author = authorService.getAuthorById(id);
            return new ResponseEntity(author, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getBookNameList")
    public ResponseEntity getBookNameList(@RequestParam("authorId")Integer authorId) {
        List<String> bookNames = authorService.getBookNames(authorId);

        return new ResponseEntity(bookNames, HttpStatus.OK);
    }
}
