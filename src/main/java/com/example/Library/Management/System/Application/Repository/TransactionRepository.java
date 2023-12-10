package com.example.Library.Management.System.Application.Repository;

import com.example.Library.Management.System.Application.Entities.Book;
import com.example.Library.Management.System.Application.Entities.LibraryCard;
import com.example.Library.Management.System.Application.Entities.Transaction;
import com.example.Library.Management.System.Application.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard libraryCard, TransactionStatus transactionStatus);
}
