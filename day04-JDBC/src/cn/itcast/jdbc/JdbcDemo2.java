package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo2 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取数据库连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basicgrammar?characterEncoding=utf8&useSSL=false", "root", "root");
            // 3. 定义sql语句
            String sql1 = "insert into account values(16, '桂纶镁', 5000)";    // insert
            String sql2 = "update account set balance = 3000 where id = 15";    // update
            String sql3 = "delete from account where id = 15";  // delete
            String sql4 = "create table test20 (id int, name varchar(20))";  // create
            // 4. 获取执行sql语句的对象
            statement = connection.createStatement();
            // 5. 执行sql语句
            // executeUpdate()方法可以执行DML和DDL语句
            // executeQuery()方法可以执行DQL语句
            int result1 = statement.executeUpdate(sql1);
            int result2 = statement.executeUpdate(sql2);
            int result3 = statement.executeUpdate(sql3);
            int result4 = statement.executeUpdate(sql4);
            // 6. 处理返回结果
            System.out.println(result1);
            System.out.println(result2);
            System.out.println(result3);
            System.out.println(result4);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 释放资源
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
