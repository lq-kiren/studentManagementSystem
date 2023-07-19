package edu.cuit.service;

import edu.cuit.beans.Student;
import edu.cuit.dao.IStudentDao;
import edu.cuit.dao.StudentDaoImpl;

import java.io.*;
import java.util.List;

public class StudentServiceImpl implements IStudentService{
    //产生一个Dao层对象
    private IStudentDao studentDao = new StudentDaoImpl();

    @Override
    public Student getStudent(String sid) {
        return studentDao.getStudent(sid);
    }

    @Override
    public List<Student> getStudents() {
        return studentDao.getStudents();
    }

    @Override
    public int saveStudent(Student student) {
        return studentDao.saveStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public int deleteStudentBySid(String sid) {
        return studentDao.deleteStudentBySid(sid);
    }

    @Override
    public int deleteAll() {
        return studentDao.deleteAll();
    }

    @Override
    public void importFromFile(String path){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String str = null;
            while ((str = reader.readLine())!=null){
                String[] line = str.split(",");
                String sid = line[0];
                String name = line[1];
                int age = Integer.parseInt(line[2]);
                String gender = line[3];
                String department = line[4];
                String major = line[5];
                Student student = new Student(sid, name, age, gender, department, major);
                saveStudent(student);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void exportToFile(String path) {
        File file = new File(path);
        BufferedWriter writer = null;
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
             writer = new BufferedWriter(new FileWriter(file));
            List<Student> students = getStudents();
            for (Student student : students){
                writer.write(student.getSid()+",");
                writer.write(student.getName()+",");
                writer.write(student.getAge()+",");
                writer.write(student.getGender()+",");
                writer.write(student.getDepartment()+",");
                writer.write(student.getMajor());
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
