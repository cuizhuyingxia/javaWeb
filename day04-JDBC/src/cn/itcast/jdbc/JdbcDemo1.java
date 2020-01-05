package cn.itcast.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. 导入mysql驱动jar包

        // 2. 注册驱动
        // 方法一，因为Driver.java中有一个静态代码块，封装了注册语句，所以将Driver.class字节码文件加载进内存，可以实现注册；因为加载类时，也会自动加载静态代码块
        // Class.forName("com.mysql.jdbc.Driver");
        // 方法二，手写注册语句
        // java.sql.DriverManager.registerDriver(new Driver());
        // 方法三，可以不用注册驱动
        // 因为自mysql5以后，驱动jar包中下的mysql-connector-java-5.1.48-bin.jar!\META-INF\services\java.sql.Driver文件中已经写好了要加载的Driver.class字节码文件的名称
        // 所以相当于是，自动注册驱动了


        // 3. 获取数据库连接对象
        // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basicgrammar?useSSL=false", "root", "root");
        // 如果要连接的mysql数据库是本机上的，并且端口是3306，那么在写url时可以省略掉ip和端口
        Connection connection = DriverManager.getConnection("jdbc:mysql:///basicgrammar?useSSL=false", "root", "root");

        // 4. 定义sql语句
        String sql0 = "start transaction";
        String sql1 = "update account set balance = 990 where id = 7";
        String sql2 = "commit";


        // 5. 获取执行sql语句的对象：Statement
        Statement statement = connection.createStatement();

        // 6. 执行sql
        int x0 = statement.executeUpdate(sql0);
        int x1 = statement.executeUpdate(sql1);
        int x2 = statement.executeUpdate(sql2);

        // 7. 处理返回的结果
        System.out.println(x0);
        System.out.println(x1);
        System.out.println(x2);


        // 8. 释放资源
        statement.close();
        connection.close();


    }
}
