package com.example.Library.Management.System.Application.Services;

import com.example.Library.Management.System.Application.Entities.Student;
import com.example.Library.Management.System.Application.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StudentServices {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender mailSender;


    public String addStudent(Student student) {

        studentRepository.save(student); //.save(student)-it automatically Inserts the Student into DB by performing INSERT Query

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String body = "Hi "+student.getName() + "You have succesfully registered. You can start issuing the books now";

        //sending Mails
        mailMessage.setFrom("springaccio@gmail.com");
        mailMessage.setTo(student.getEmailId());
        mailMessage.setSubject("Welcome to Spectra High School's Library !!");
        mailMessage.setText(body);

        mailSender.send(mailMessage);

        return "Student has been saved to the DB.";
    }
}
