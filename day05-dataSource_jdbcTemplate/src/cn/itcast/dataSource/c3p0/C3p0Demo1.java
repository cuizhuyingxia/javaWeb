package cn.itcast.dataSource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
数据库连接池c3p0的演示
 */
public class C3p0Demo1 {
    public static void main(String[] args) throws SQLException {
        // 1. 创建c3p0连接池对象
        DataSource dataSource = new ComboPooledDataSource();
        // 2. 从连接池中获取连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);


    }
}
