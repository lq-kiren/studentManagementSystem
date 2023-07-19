package edu.cuit.dao;

import edu.cuit.beans.Student;
import edu.cuit.utils.DbHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao{

    @Override
    public Student getStudent(String sid_) {
        Student student = null;
        String sql = "select * from students where sid=?";
        ResultSet rs = DbHelper.getResultSet(sql, sid_);
        try {
            if (rs.next()){
                String sid = rs.getString("sid");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String department = rs.getString("department");
                String major = rs.getString("major");
                student = new Student(sid, name, age, gender, department, major);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "select * from students";
        ResultSet rs = DbHelper.getResultSet(sql, null);
        try {
            while (rs.next()){
                String sid = rs.getString("sid");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String department = rs.getString("department");
                String major = rs.getString("major");
                Student student = new Student(sid, name, age, gender, department, major);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public int saveStudent(Student student)  {
        String sql = "insert into students(sid, name, age, gender, department, major)"+
                " values(?,?,?,?,?,?)";
        int res = DbHelper.update(sql, student.getSid(), student.getName(), student.getAge(),
                student.getGender(), student.getDepartment(), student.getMajor());
        return res;
    }

    @Override
    public int updateStudent(Student student) {
        String sql = "update students set name=?, age=?, gender=?, " +
                "department=?, major=? where sid=?";
        int res = DbHelper.update(sql, student.getName(), student.getAge(),
                student.getGender(), student.getDepartment(), student.getMajor(), student.getSid());
        return res;
    }

    @Override
    public int deleteStudentBySid(String sid) {
        String sql = "delete from students where sid=?";
        int res = DbHelper.update(sql, sid);
        return res;
    }

    @Override
    public int deleteAll() {
        String sql = "delete from students";
        int res = DbHelper.update(sql, null);
        return res;
    }
}
