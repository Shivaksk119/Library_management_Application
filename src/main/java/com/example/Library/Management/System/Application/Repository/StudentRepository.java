package com.example.Library.Management.System.Application.Repository;

import com.example.Library.Management.System.Application.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// we can use methods in the JpaRepository for basic CRUD operations
public interface StudentRepository extends JpaRepository<Student, Integer> { //the input params are entity and primary key of the entity


}
