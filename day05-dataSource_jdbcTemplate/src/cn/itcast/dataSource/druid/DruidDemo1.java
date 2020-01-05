package cn.itcast.dataSource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo1 {
    public static void main(String[] args) throws Exception {
        // 1. 导入jar包
        // 2. 定义配置文件

        // 3. 加载配置文件
        InputStream resourceAsStream = DruidDemo1.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        // 4. 使用工厂类获取数据库连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        // 5. 获取数据库连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
