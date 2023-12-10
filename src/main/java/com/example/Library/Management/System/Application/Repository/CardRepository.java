package com.example.Library.Management.System.Application.Repository;

import com.example.Library.Management.System.Application.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<LibraryCard, Integer> {

}
