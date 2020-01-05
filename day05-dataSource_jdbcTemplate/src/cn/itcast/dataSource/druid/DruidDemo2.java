package cn.itcast.dataSource.druid;

import cn.itcast.dataSource.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DruidDemo2 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 1. 获取数据库连接对象
            connection = JDBCUtils.getConnection();
            // 2. 定义sql语句
            String sql = "insert into account values(null, ?, ?)";
            // 3. 获取执行sql语句的对象PreparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 4. 给?赋值
            preparedStatement.setString(1, "tutu");
            preparedStatement.setDouble(2, 2000);
            // 5. 执行sql语句
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            JDBCUtils.close(preparedStatement, connection);
        }

    }
}
