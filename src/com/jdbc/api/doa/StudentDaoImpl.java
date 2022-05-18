package com.jdbc.api.doa;

import com.jdbc.api.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public List<Student> findAllStudent() {
        String sql = "SELECT * FROM STUDENT";

        List<Student> studentList = jdbcTemplate.query(sql, new StudentRowMapper());

        for (Student student : studentList){
            System.out.println("Students:::: " + student.toString());
        }

        return studentList;
    }

    @Override
    public Student findStudentById(int rollNo) {

        String sql = "SELECT * FROM STUDENT WHERE roll_no = ?";
        Student foundStudent = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), rollNo);
        assert foundStudent != null;
        System.out.println("Student found: " + foundStudent);
        return foundStudent;
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

    class StudentRowMapper implements RowMapper<Student>{

        /**
         * Implementations must implement this method to map each row of data
         * in the ResultSet. This method should not call {@code next()} on
         * the ResultSet; it is only supposed to map values of the current row.
         *
         * @param rs     the ResultSet to map (pre-initialized for the current row)
         * @param rowNum the number of the current row
         * @return the result object for the current row (may be {@code null})
         * @throws SQLException if an SQLException is encountered getting
         *                      column values (that is, there's no need to catch SQLException)
         */
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

            Student newStudent = new Student();

            newStudent.setRollNo(rs.getInt("roll_no"));
            newStudent.setStudentName(rs.getString("student_name"));
            newStudent.setStudentAddress(rs.getString("student_address"));

            System.out.println("mapRow() called:::::");

            return newStudent;
        }
    }
}
