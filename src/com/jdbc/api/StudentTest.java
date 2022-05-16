package com.jdbc.api;

import com.jdbc.api.doa.StudentDao;
import com.jdbc.api.doa.StudentDaoImpl;

public class StudentTest {



    public static void main(String[] args) {
        Student mike = new Student();

//        mike.setRollNo(5);
//        mike.setStudentName("Chidozie Ebuka");
//        mike.setStudentAddress("Lagos, Nigeria");
//
        StudentDao repo = new StudentDaoImpl();
//        repo.insert(mike);

        repo.delete(5);
    }
}
