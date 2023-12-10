package com.example.Library.Management.System.Application.Services;

import com.example.Library.Management.System.Application.Entities.Book;
import com.example.Library.Management.System.Application.Entities.LibraryCard;
import com.example.Library.Management.System.Application.Entities.Transaction;
import com.example.Library.Management.System.Application.Enums.CardStatus;
import com.example.Library.Management.System.Application.Enums.TransactionStatus;
import com.example.Library.Management.System.Application.Exceptions.*;
import com.example.Library.Management.System.Application.Repository.BookRepository;
import com.example.Library.Management.System.Application.Repository.CardRepository;
import com.example.Library.Management.System.Application.Repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.TransactionAnnotationParser;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final int maxLimitOfBooks = 3;

    private static final int finePerDay = 5;

    public String issueBook(Integer bookId, Integer cardId) throws Exception{

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING); // Initially it is pending

        //Validations

        //valid BookId
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(!optionalBook.isPresent()) {
            throw new BookNotFoundException("BookId entered is invalid");
        }

        Book book = optionalBook.get();

        if(!book.isAvailabilityOfBook()) {
            throw new BookNotAvailableException("Book you've looking for is Unavailable");
        }

        //valid cardId
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);

        if(!optionalLibraryCard.isPresent()) {
            throw new CardNotFoundException("CardId Entered is Invalid");
        }

        LibraryCard libraryCard = optionalLibraryCard.get();

        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)) {
            throw new InvalidCardStatusException("Card status is not ACTIVE");
        }

        //Max Limit = 3;
        if(libraryCard.getNoOfBooksIssued()>=maxLimitOfBooks) {
            throw new MaxBooksAlreadyIssued("You can only get maximum "+maxLimitOfBooks+" books issued on your card");
        }

        //creating transaction Entity;

        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()+1);

        book.setAvailabilityOfBook(false); // Book is no longer available

        //setting foreign KEY
        transaction.setBook(book);
        transaction.setCard(libraryCard);

        //Saving the relevant Entities
        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

//        //save the parent Entities
//        bookRepository.save(book);
//        cardRepository.save(libraryCard);

        //Instead of saving the both parents we will be saving only child to avoid transaction entity 2 times
        transactionRepository.save(transaction);

        return "The Book with the bookId of "+bookId+" has been issued on your card with cardId of "+cardId;
    }

    public String returnBook(Integer bookId, Integer cardId) {
        Book book =  bookRepository.findById(bookId).get();

        LibraryCard libraryCard = cardRepository.findById(cardId).get();

        //I need to find that  out that issue Transaction
        Transaction transaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book, libraryCard, TransactionStatus.ISSUED);

        Date issuedDate =  transaction.getCreatedOn();

        //Predefined method that you can use to calculate days;
        long milliSeconds = Math.abs(System.currentTimeMillis()-issuedDate.getTime());
        long days = TimeUnit.DAYS.convert(milliSeconds, TimeUnit.MILLISECONDS);

        int fineAmount = 0;

        if(days>15) {
            fineAmount = (int) ((days-15)*finePerDay);
        }

        Transaction newTransaction =  new Transaction();

        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        //setting Foreign keys
        newTransaction.setBook(book);
        newTransaction.setCard(libraryCard);

        book.setAvailabilityOfBook(true);
        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()-1);

        book.getTransactionList().add(newTransaction);
        libraryCard.getTransactionList().add(newTransaction);

        transactionRepository.save(newTransaction);

        return "Your Book has been returned";

    }
}
