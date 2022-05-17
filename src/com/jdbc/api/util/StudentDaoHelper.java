package com.jdbc.api.util;

import com.jdbc.api.doa.StudentDaoImpl;
import com.jdbc.api.model.Student;

import java.util.ArrayList;

public class StudentDaoHelper {

    StudentDaoImpl studentDao = new StudentDaoImpl();

    public void setUpStudentTable(){

        Student student1 = new Student();
        student1.setRollNo(1);
        student1.setStudentName("Mike");
        student1.setStudentAddress("Nig");

        Student student2 = new Student();
        student2.setRollNo(2);
        student2.setStudentName("Ebuks");
        student2.setStudentAddress("ABJ");

        Student student3 = new Student();
        student1.setRollNo(3);
        student3.setStudentName("Abigail");
        student1.setStudentAddress("Ikoyi");

        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        studentDao.multipleInsert(students);
    }
}
