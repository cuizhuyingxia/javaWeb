package cn.itcast.jdbc;

import java.sql.*;

public class JdbcDemo3 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取数据库连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basicgrammar?characterEncoding=utf8&useSSL=false", "root", "root");
            // 3. 定义sql语句
            String sql = "select * from account";
            // 4. 获取执行sql语句的对象
            statement = connection.createStatement();
            // 5. 执行sql语句
            resultSet = statement.executeQuery(sql);
            // 6. 处理返回结果
            // 返回的结果集其实就是一个游标
            // 使用循环获取游标中的数据
            while (resultSet.next()) {  // next()方法可以使游标向下移动一行，如果这一行有数据，则返回true，否则返回false
                // 获取每个字段的值
                int id = resultSet.getInt("id");
                String name = resultSet.getString(2);
                double balance = resultSet.getDouble("balance");
                System.out.println(id + " " + name + " " + balance);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 释放资源
            if (resultSet != null) {    // 避免空指针异常
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (statement != null) {    // 避免空指针异常
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {   // 避免空指针异常
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
