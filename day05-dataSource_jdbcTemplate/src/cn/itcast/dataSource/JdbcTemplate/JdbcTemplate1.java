package cn.itcast.dataSource.JdbcTemplate;

import cn.itcast.dataSource.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplate1 {
    public static void main(String[] args) {
        // 1. 导入jar包
        // 2. 创建JdbcTemplate对象
        // 需要传入数据库连接池对象作为参数，然后JdbcTemplate会自动去获取数据库连接对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        // 3. 定义sql
        String sql = "update account set balance = ? where id = ?";
        // 4. 执行sql
        int count = jdbcTemplate.update(sql, 500, 2);   // 传入sql语句和参数
        System.out.println(count);
        // JdbcTemplate会自动帮我们释放资源
    }
}
