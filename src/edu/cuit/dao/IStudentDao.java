package edu.cuit.dao;

import edu.cuit.beans.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDao {
    Student getStudent(String sid);  //根据sid找到student
    List<Student> getStudents();     //获取所有student
    int saveStudent(Student student);
    int updateStudent(Student student);
    int deleteStudentBySid(String sid); //根据学号删除
    int deleteAll();
}
