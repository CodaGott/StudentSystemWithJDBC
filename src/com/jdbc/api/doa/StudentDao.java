package com.jdbc.api.doa;

import com.jdbc.api.model.Student;

import java.util.List;

public interface StudentDao {

    void insert(Student student);

    Boolean delete(int id);

    Boolean deleteStudentByNameAndAddress(String studentName, String studentAddress);

    void multipleInsert(List<Student>students);

    List<Student> findAllStudent();

    Student findStudentById(int rollNo);
}
