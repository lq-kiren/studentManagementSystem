package edu.cuit.beans;

import java.util.Objects;

public class Student {
    private String sid;//学号设置为Unique
    private String name;
    private int age;
    private String gender;
    private String department;
    private String major;

    public Student() {
    }

    public Student(String sid, String name, int age, String gender, String department, String major) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.major = major;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(sid, student.sid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid);
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
