package cn.itcast.jdbc;

import cn.itcast.domain.Account;
import cn.itcast.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/*
定义一个方法，查询account表的数据并将其封装为对象，然后装载到集合中，最后返回集合
 */
public class JdbcDemo4 {
    public static void main(String[] args) {
        JdbcDemo4 jdbcDemo4 = new JdbcDemo4();
        // 9. 调用findAll()方法测试效果
        List<Account> all = jdbcDemo4.findAll2();
        for (Account account : all) {
            System.out.println(account);
        }
        System.out.println(all.size());
    }

    public List<Account> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Account> accounts = null;
        try {
            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取数据库连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basicgrammar?characterEncoding=utf8&useSSL=false", "root", "root");
            // 3. 获取执行sql语句的对象
            statement = connection.createStatement();
            // 4. 定义sql语句
            String sql = "select * from account";
            // 5. 执行sql语句
            resultSet = statement.executeQuery(sql);
            // 6. 处理返回结果
            accounts = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");

                // 7. 将查询到的数据封装到Account对象中，并将封装好的对象添加到集合中
                accounts.add(new Account(id, name, balance));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {    // 防止空指针异常：如果程序在打开resultSet资源前就已经发生了异常，则不用调用close()方法关闭资源，否则会出现空指针异常
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 8. 返回集合
        return accounts;
    }


    /*
    演示JDBC工具类
     */
    public List<Account> findAll2() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Account> accounts = null;
        try {
            // 注册驱动，并获取数据库连接对象
            connection = JDBCUtils.getConnection();
            // 3. 获取执行sql语句的对象
            statement = connection.createStatement();
            // 4. 定义sql语句
            String sql = "select * from account";
            // 5. 执行sql语句
            resultSet = statement.executeQuery(sql);
            // 6. 处理返回结果
            accounts = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");
                // 7. 将查询到的数据封装到Account对象中，并将封装好的对象添加到集合中
                accounts.add(new Account(id, name, balance));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtils.close(resultSet, statement, connection);
        }
        // 8. 返回集合
        return accounts;
    }
}
