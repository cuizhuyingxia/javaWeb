package cn.itcast.dataSource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Csp0Demo2 {
    public static void main(String[] args) throws SQLException {
        // 1. 获取c3p0连接池对象
        DataSource dataSource = new ComboPooledDataSource("otherc3p0");
        // 2. 测试最大连接数
        for (int i = 1; i <= 11; i++) {
            Connection connection = dataSource.getConnection();
            System.out.println(i + "    " + connection);
        }


















    }
}
