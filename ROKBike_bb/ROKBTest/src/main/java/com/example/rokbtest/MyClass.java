package com.example.rokbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyClass {
    public static Connection getConn() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://ec2-3-35-21-60.ap-northeast-2.compute.amazonaws.com:3306";
            String user = "root";
            String pw = "qwer1234";

            Class.forName("org.mariadb.jdbc.Driver");
//            Driver driver = new Driver();
//            Properties props = null;
//            props.setProperty("user", user);
//            props.setProperty("password", pw);
//            driver.connect(url, props);
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("커넥션된거임 암튼그럼111");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("클래스 ㄴㄴ");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("쿼리 ㄴㄴ");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("완전 예외");
        }
        System.out.println("커넥션된거임 암튼그럼222");
        return conn;
    }
}
