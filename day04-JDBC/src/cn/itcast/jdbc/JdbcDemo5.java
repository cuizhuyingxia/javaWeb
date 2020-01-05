package cn.itcast.jdbc;

import cn.itcast.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/*
需求：
1. 通过键盘录入用户名和密码
2. 判断用户是否登录成功
 */
public class JdbcDemo5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();
        boolean login = new JdbcDemo5().login2(username, password);
        if (login) {
            System.out.println("登录成功");
        } else {
            System.out.println("用户名或密码错误");
        }
    }

    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 注册驱动，获取数据库连接对象
            connection = JDBCUtils.getConnection();
            // 定义sql语句
            String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
            /*
            sql注入
            请输入用户名
            hjhjlhlk
            请输入密码
            a' or 'a' = 'a
            select * from user where username = 'hjhjlhlk' and password = 'a' or 'a' = 'a'
            登录成功
             */
            System.out.println(sql);
            // 获取执行sql语句的对象
            statement = connection.createStatement();
            // 执行sql语句
            resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, statement, connection);
        }
        // 默认返回false
        return false;
    }


    /*
    使用PreparedStatement对象解决sql注入问题
    */
    public boolean login2(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;
        try {
            // 注册驱动，获取数据库连接对象
            connection = JDBCUtils.getConnection();
            // 定义sql语句：参数使用?作占位符
            String sql = "select * from user where username = ? and password = ?";
            // 获取执行sql语句的对象PreparedStatement，需要传入sql作为参数
            preparedStatement = connection.prepareStatement(sql);
            // 给?赋值
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            // 执行sql语句，不需要传入sql作为参数
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
        // 默认返回false
        return false;
    }
}
