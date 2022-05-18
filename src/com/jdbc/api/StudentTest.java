package com.jdbc.api;

import com.jdbc.api.doa.StudentDao;
import com.jdbc.api.doa.StudentDaoImpl;
import com.jdbc.api.model.Student;

public class StudentTest {



    public static void main(String[] args) {
        Student mike = new Student();

        mike.setRollNo(5);
        mike.setStudentName("Chidozie Ebuka");
        mike.setStudentAddress("Anambra, Nigeria");
//
        StudentDao repo = new StudentDaoImpl();
//        repo.insert(mike);

//        repo.deleteStudentByNameAndAddress("Chidozie",
//                "Lagos, Nigeria");
//
//
        StudentDaoImpl cleanUp = new StudentDaoImpl();
//        cleanUp.cleanUpTable();
//
//        StudentDaoHelper helper = new StudentDaoHelper();
//        helper.setUpStudentTable();

//        cleanUp.findAllStudent();

        cleanUp.findStudentById(2);
    }
}
