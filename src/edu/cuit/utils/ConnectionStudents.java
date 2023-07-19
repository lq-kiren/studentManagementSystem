package edu.cuit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionStudents {

    private static ConnectionStudents connectionStudents = new ConnectionStudents();

    //单例模式，连接数据库
    private ConnectionStudents(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/student_management_system?"
                + "characterEncoding=utf-8&useUnicode=true&useSSL=true&serverTimezone=UTC";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
