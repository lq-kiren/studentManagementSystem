package edu.cuit.utils;

import java.sql.*;


//通用类，进行增删改查
public class DbHelper {
    private static Connection conn = ConnectionStudents.getConnection();

    public static ResultSet getResultSet(String sql, Object...params){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            if(params != null){
                for (int i = 0; i < params.length; i++) {
                    //设置占位符参数
                    pstmt.setObject(i+1, params[i]);
                }
            }
            //执行sql语句，获取返回集
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    //增删改函数，返回收影响行数
    public static int update(String sql, Object...params) {
        int res=0;
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i+1, params[i]);
                }
            }
            res = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    //关闭资源
    public static void close(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Statement stmt){
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
