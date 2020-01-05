package cn.itcast.jdbc;

import cn.itcast.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDemo6 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            // 1. 获取连接
            connection = JDBCUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);    // 因为mysql中，DML语句会自动进行commit，关闭自动commit后，就相当于是开启了事务
            // 2. 定义qsl
            String sql1 = "update account set balance = balance - ? where id = ?";
            String sql2 = "update account set balance = balance + ? where id = ?";
            // 3. 获取执行sql语句的对象PreparedStatement
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement2 = connection.prepareStatement(sql2);
            // 4. 给?赋值
            preparedStatement1.setDouble(1, 500);
            preparedStatement1.setInt(2, 1);
            preparedStatement2.setDouble(1, 500);
            preparedStatement2.setInt(2, 2);
            // 5. 执行sql
            preparedStatement1.execute();
            // 手动设置一个异常
            int num = 3 / 0;
            preparedStatement2.execute();
            // 提交事务
            connection.commit();
        } catch (Exception e) {
            // 回滚事务
            try {
                if (connection != null) {   // 如果在没有获取到数据库连接前就发生了异常，则不进行回滚
                                            // 因为事务是在获取数据库连接之后开启的，所以当没有获取到数据库连接对象时，也就意味着事务没有被开启，
                                            // 此时执行rollback就会发生空指针异常
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement1, connection);
            JDBCUtils.close(preparedStatement2, null);
        }




























    }
}
