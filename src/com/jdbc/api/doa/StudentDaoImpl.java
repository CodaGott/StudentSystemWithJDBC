package com.jdbc.api.doa;

import com.jdbc.api.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao{



    @Autowired
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

    @Override
    public Boolean delete(int rollNo) {
        String sql = "DELETE FROM STUDENT WHERE ROLL_NO = ?";

        int numDeletedRow = jdbcTemplate.update(sql, rollNo);
        System.out.println("Number of rows deleted: " + numDeletedRow);
        return numDeletedRow >= 1;
    }

    @Override
    public Boolean deleteStudentByNameAndAddress(String studentName, String studentAddress) {

        String sql = "DELETE FROM STUDENT WHERE STUDENT_NAME = ? OR STUDENT_ADDRESS = ?";

        Object[] students = {studentName, studentAddress};
        int numberOfItemsDeleted = jdbcTemplate.update(sql, students);
        System.out.println("Number of items deleted: " + numberOfItemsDeleted);
        return numberOfItemsDeleted >= 1;
    }

    @Override
    public void multipleInsert(List<Student> students) {
        String sql = "INSERT INTO STUDENT(ROLL_NO, STUDENT_NAME, STUDENT_ADDRESS) VALUES (?,?,?)";
        ArrayList<Object[]> sqlArgs = new ArrayList<>();

        for (Student student: students) {

            Object[] studentData = {
            student.getRollNo(),
            student.getStudentName(),
            student.getStudentAddress() };
            sqlArgs.add(studentData);
        }
        jdbcTemplate.batchUpdate(sql, sqlArgs);
        System.out.println("Batch completed !!!");
    }

    public DataSource getDataSource() {
        String url = "jdbc:mysql://localhost:3306/school?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String userName = "root";
        String password = "";
        DataSource dataSource = new DriverManagerDataSource(url, userName, password);
        return dataSource;
    }

    public void cleanUpTable(){
        String sql = "TRUNCATE TABLE STUDENT";
        jdbcTemplate.execute(sql);
    }
}
