package cn.itcast.dataSource.JdbcTemplate;


import cn.itcast.dataSource.domain.Account;
import cn.itcast.dataSource.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/*
使用JdbcTemplate操作account表，加深对JdbcTemplate的使用
 */
public class JdbcTemplateDemo2 {
    // 获取数据库连接对象
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());


    // 1. 修改id为1的记录的balance字段为10000
    @Test
    public void test1() {
        String sql = "update account set balance = ? where id = ?";
        int count = jdbcTemplate.update(sql, 3000, 1);
        System.out.println(count);
    }

    // 2. 添加一条记录
    @Test
    public void test2() {
        String sql = "insert into account values(?, ?, ?)";
        int count = jdbcTemplate.update(sql, 3, "tutu", 2000);
        System.out.println(count);
    }

    // 3. 删除刚才添加的记录
    @Test
    public void test3() {
        String sql = "delete from account where id = ?";
        int count = jdbcTemplate.update(sql, 3);
        System.out.println(count);
    }

    // 4. 查询id为1的记录，并将其封装为map集合
    @Test
    public void test4() {
        String sql = "select * from account where id = ?";
        // 注意：queryForMap()方法一次只能查询一条记录
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql, 1);
        System.out.println(stringObjectMap);    // {id=1, name=coco, balance=3000.0}
    }

    // 5. 查询所有的记录，将其封装为list集合
    @Test
    public void test5() {
        String sql = "select * from account";
        // 注意：queryForList()方法是先将每一条记录封装为map集合，然后再将map集合装载到list集合中
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }

    // 6. 查询所有记录，将其封装为Account对象的List集合
    @Test
    public void test6_1() {
        String sql = "select * from account;";
        // 调用query()方法，并实现RowMapper接口中的mapRow()方法，将每条记录封装为Account对象，并返回每条记录的Account对象，然后使用list集合接收
        List<Account> list = jdbcTemplate.query(sql, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                // 获取每条记录的每个字段的值
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");

                // 创建Account对象
                Account account = new Account();

                // 将每条记录的每个字段的值封装到Account对象中
                account.setId(id);
                account.setName(name);
                account.setBalance(balance);

                // 返回Account对象
                return account;
            }
        });

        for (Account account : list) {
            System.out.println(account);
        }
    }

    @Test
    public void test6_2() {
        String sql = "select * from account;";
        // 调用query()方法，创建BeanPropertyRowMapper类对象，BeanPropertyRowMapper类已经实现了RowMapper接口，会自动将数据封装到JavaBean中（对于当前需求来说，JavaBean指的就是Account对象）
        // 所以我们不用去手动实现RowMapper接口中的mapRow()方法了，不过需要传入要封装的对象的类名和Class对象
        List<Account> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Account>(Account.class));
        for (Account account : list) {
            System.out.println(account);
        }
    }

    // 7. 查询总记录数
    @Test
    public void test7() {
        String sql = "select count(id) from account";
        // 调用queryForObject()方法，传入sql和返回值类型的Class对象，例如sql语句返回的结果是Long类型，那么就传入Long.class
        Long total = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(total);

    }

























}
