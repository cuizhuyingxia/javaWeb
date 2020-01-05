package cn.itcast.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/*
自定义JDBC工具类
 */
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {

        try {
            // 1. 加载配置文件
            // 获取当前类的类加载器
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            // 调用当前类的类加载器的getResourse()方法，获取src目录下的配置文件jdbc.properties的统一资源定位符
            URL resource = classLoader.getResource("jdbc.properties");
            // 获取统一资源定位符的字符串路径
            String path = resource.getPath();
            // 创建Properties对象
            Properties properties = new Properties();
            // 调用load()方法加载配置文件
            properties.load(new FileReader(path));
            // 2. 获取配置文件中的数据，并赋值给静态变量
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            // 3. 注册驱动
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    获取数据库连接对象
     */
    // 当调用静态方法时，类会被加载，同时静态代码块也会被加载
    // 静态代码块被加载后，驱动就注册成功了，而且静态变量都会被赋值
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /*
    释放资源
     */
    public static void close(Statement statement, Connection connection) {
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

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
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
}
