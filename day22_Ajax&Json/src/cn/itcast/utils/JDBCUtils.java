package cn.itcast.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource dataSource = null;

    // 使用静态代码块初始化数据库连接池
    static {
        try {
            // 1. 加载连接池配置文件
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

    // 获取连接池对象
    public static DataSource getDataSource() {
        return dataSource;
    }
}
