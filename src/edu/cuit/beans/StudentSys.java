package edu.cuit.beans;

import edu.cuit.service.IStudentService;
import edu.cuit.service.StudentServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentSys {
    private static Scanner sc = new Scanner(System.in);
    private static IStudentService studentService = new StudentServiceImpl();

    //显示主菜单
    public void mainMenu() {
        while(true){
            System.out.println("--------欢迎来到学生管理系统---------");
            System.out.println("------------1.添加学生------------");
            System.out.println("------------2.删除学生------------");
            System.out.println("------------3.修改学生------------");
            System.out.println("------------4.查找学生------------");
            System.out.println("------------5.查看所有------------");
            System.out.println("------------6.删除所有------------");
            System.out.println("------------7.导入数据------------");
            System.out.println("------------8.导出数据------------");
            System.out.println("------------9.退出系统------------");
            System.out.print("请输入你的选择：");

            String s = sc.nextLine();

            switch (s) {
                case "1":
                    System.out.println("添加学生");
                    addStudent();
                    clear();
                    break;
                case "2":
                    System.out.println("删除学生");
                    deleteStudent();
                    clear();
                    break;
                case "3":
                    System.out.println("修改学生");
                    changeStudent();
                    clear();
                    break;
                case "4":
                    System.out.println("查找学生");
                    findStudent();
                    clear();
                    break;
                case "5":
                    System.out.println("查看所有");
                    findAllStuents();
                    clear();
                    break;
                case "6":
                    System.out.println("删除所有");
                    deleteAllStudents();
                    clear();
                    break;
                case "7":
                    System.out.println("导入数据");
                    importdata();
                    clear();
                    break;
                case "8":
                    System.out.println("导出数据");
                    exportdata();
                    clear();
                case "9":
                    System.out.println("退出系统");
                    System.exit(0);
                default:
                    System.out.println("输入有误");
                    break;
            }
        }
    }

    //清空控制台
    private void clear(){
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    //添加学生
    private void addStudent() {
        System.out.print("请输入学号：");
        String sid = sc.nextLine();
        //在数据库中查找是否有？？？
        Student s_ = studentService.getStudent(sid);
        if (s_ != null){
            System.out.println("学生已存在");
            return;
        }
        System.out.print("请输入姓名：");
        String name = sc.next();
        System.out.print("请输入年龄：");
        int age = sc.nextInt();
        System.out.print("请输入性别：");
        String gender = sc.next();
        System.out.print("请输入学院信息：");
        String department = sc.next();
        System.out.print("请输入您的专业：");
        String major = sc.next();
        String s = sc.nextLine();

        Student student = new Student(sid, name, age, gender, department, major);
        int i = studentService.saveStudent(student);
        if (i > 0){
            System.out.println("录入数据成功");
        }else {
            System.out.println("录入数据失败");
        }
    }

    private void deleteStudent() {
        System.out.print("请输入要删除学生的学号：");
        String sid = sc.next();
        String s = sc.nextLine();
        int i = studentService.deleteStudentBySid(sid);
        if (i > 0){
            System.out.println(sid+"学生删除成功");
        }else{
            System.out.println("学生删除失败");
        }
    }

    private void changeStudent(){
        System.out.print("请输入学号：");
        String sid = sc.next();
        //查找是否有该学生

        System.out.print("请输入姓名：");
        String name = sc.next();
        System.out.print("请输入年龄：");
        int age = sc.nextInt();
        System.out.print("请输入性别：");
        String gender = sc.next();
        System.out.print("请输入学院信息：");
        String department = sc.next();
        System.out.print("请输入您的专业：");
        String major = sc.next();
        String s = sc.nextLine();

        Student student = new Student(sid, name, age, gender, department, major);
        int i = studentService.updateStudent(student);
        if (i > 0){
            System.out.println(sid+"学生信息更新成功");
        }else{
            System.out.println("学生信息更新失败");
        }
    }

    private void findStudent(){
        System.out.print("请输入要查找的学生学号：");
        String sid = sc.next();
        String s = sc.nextLine();
        Student student = studentService.getStudent(sid);
        if (student != null){
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", "学号","姓名","年龄","性别","学院","专业");
            System.out.printf("%-15s %-15s %-15d %-15s %-15s %-15s\n", student.getSid(), student.getName(),
                    student.getAge(), student.getGender(), student.getDepartment(), student.getMajor());
        }else{
            System.out.println("未找到该学生");
        }
    }

    private void findAllStuents() {
        List<Student> students = studentService.getStudents();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", "学号","姓名","年龄","性别","学院","专业");
        for (Student student: students){
            System.out.printf("%-15s %-15s %-15d %-15s %-15s %-15s\n", student.getSid(), student.getName(),
                    student.getAge(), student.getGender(), student.getDepartment(), student.getMajor());
        }
        System.out.println();
    }

    private void deleteAllStudents(){
        System.out.println("确认是否删除所有学生信息！！！：");
        String s = sc.nextLine();
        if (s == "1" || s == "是" || s== "yes"|| s=="y"){
            studentService.deleteAll();
        }else{
            System.out.println("不删除");
        }
        return;
    }

    private void importdata(){
        System.out.println("请输入文件路径：");
        String filepath = sc.nextLine();
        try {
            studentService.importFromFile(filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void exportdata(){
        System.out.print("请输入要保存的路径：");
        String filepath = sc.nextLine();
        try {
            studentService.exportToFile(filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
