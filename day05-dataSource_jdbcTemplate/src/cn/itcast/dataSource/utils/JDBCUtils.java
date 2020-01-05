package cn.itcast.dataSource.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
Druid连接池工具类
 */
public class JDBCUtils {
    private static DataSource dataSource = null;
    public String name;

    // 使用静态代码块初始化数据库连接池
    static {
        try {
            // 1. 加载配置文件
            Properties properties = new Properties();
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));

            // 2. 使用工厂类获取数据库连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 获取数据库连接对象
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // 释放资源
    public static void close(Statement statement, Connection connection) {
        /*if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();     // 归还给连接池
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        // 简写为
        close(null, statement, connection);
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
                connection.close();     // 归还给连接池
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 获取数据库连接池对象
    public static DataSource getDataSource() {
        return dataSource;
    }

}
