package com.example.Library.Management.System.Application.Services;

import com.example.Library.Management.System.Application.DTOs.AddBookRequest;
import com.example.Library.Management.System.Application.Entities.Author;
import com.example.Library.Management.System.Application.Entities.Book;
import com.example.Library.Management.System.Application.Enums.Genre;
import com.example.Library.Management.System.Application.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.System.Application.Repository.AuthorRepository;
import com.example.Library.Management.System.Application.Repository.BookRepository;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(AddBookRequest bookRequest) throws Exception {
        // we have received a DTO object Here
        // Convert it to  Entity and Save it to DB

        Book book = new Book();
        book.setBookName(bookRequest.getBookName());
        book.setNoOfPages(bookRequest.getNoOfPages());
        book.setGenre(bookRequest.getGenre());
        book.setPrice(bookRequest.getPrice());
        book.setAvailabilityOfBook(bookRequest.isAvailabilityOfBook());

        Integer authorId = bookRequest.getAuthorId();

        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(!optionalAuthor.isPresent()) {
            throw new AuthorNotFoundException("Author Id Entered is invalid");
        }

        Author author = optionalAuthor.get();

        book.setAuthor(author);

        //bcz it's Bidirectional mapping
        //Author should also have the information of the Book Entity
        author.getBookList().add(book);

        authorRepository.save(author);

        return "Book has been added to the DB";

    }

    public String addBook(Book book, Integer authorId) throws Exception {

        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(!optionalAuthor.isPresent()) {
            throw new AuthorNotFoundException("Author Id Entered is invalid");
        }

        Author author = optionalAuthor.get();

        book.setAuthor(author);

        //bcz it's Bidirectional mapping
        //Author should also have the information of the Book Entity
        author.getBookList().add(book);

        authorRepository.save(author);

        return "Book has been added to the DB";

    }

    public List<String> getBooksByGenre(Genre genre){

        List<Book> bookList = bookRepository.findBooksByGenre(genre);

        List<String> bookNames = new ArrayList<>();

        for(Book book:bookList) {

            bookNames.add(book.getBookName());
        }
        return bookNames;
    }



}
