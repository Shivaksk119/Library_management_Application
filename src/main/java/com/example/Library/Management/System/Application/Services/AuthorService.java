package com.example.Library.Management.System.Application.Services;

import com.example.Library.Management.System.Application.Entities.Author;
import com.example.Library.Management.System.Application.Entities.Book;
import com.example.Library.Management.System.Application.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "Author has been added to the DB";
    }

    public List<String> getAllAuthorNames() {
        List<Author> authors = authorRepository.findAll();

        List<String> authorNames = new ArrayList<>();

        for(Author author:authors) {
            authorNames.add(author.getAuthorName());
        }
        return authorNames;
    }

    public Author getAuthorById(Integer id) throws Exception {

        Optional<Author> optionalAuthor = authorRepository.findById(id); // Optional can return the source data type if exist if not return null

        if(!optionalAuthor.isPresent()) { //if it is null -- that means if it is doesn't get any object
            //Throw an error
            throw new Exception("The Id Entered is Invalid");

        }

        Author author = optionalAuthor.get();
        return author;

    }

    public List<String> getBookNames(Integer authorId) {

        List<String> bookNames = new ArrayList<>();

        Author author = authorRepository.findById(authorId).get();

        List<Book> bookList = author.getBookList();

        for(Book book:bookList) {
            bookNames.add(book.getBookName());
        }
        return bookNames;
    }
}
