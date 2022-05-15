package com.jdbc.api.doa;

import com.jdbc.api.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class StudentDaoImpl implements StudentDao{



    private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

    @Override
    public void insert(Student student) {
        String sql = "INSERT INTO STUDENT VALUES(?,?,?) ";
        Object[] students = {student.getRollNo(),
        student.getStudentName(),
        student.getStudentAddress()};
        int numOfRowInserted = jdbcTemplate.update(sql, students);

        System.out.println("Number of row inserted is: " + numOfRowInserted);
    }

    public DataSource getDataSource() {
        String url = "jdbc:mysql://localhost:3306/school?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String userName = "root";
        String password = "";
        DataSource dataSource = new DriverManagerDataSource(url, userName, password);
        return dataSource;
    }
}
