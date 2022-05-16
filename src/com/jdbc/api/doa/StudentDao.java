package com.jdbc.api.doa;

import com.jdbc.api.Student;

public interface StudentDao {

    void insert(Student student);

    Boolean delete(int id);
}
