package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/*
操作数据库
 */
public class UserDao {
    // 创建JdbcTemplate对象
    // 需要传入数据库连接池对象作为参数，然后JdbcTemplate会自动去获取数据库连接对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser
     * @return
     */
    public User login(User loginUser) {
        try {
            // 1. 编写sql
            String sql = "select * from user where username = ? and password = ?";
            // 2. 查询
            // 调用queryForObject()方法，创建BeanPropertyRowMapper类对象，BeanPropertyRowMapper类已经实现了RowMapper接口，会自动将数据封装到JavaBean中（对于当前需求来说，JavaBean指的就是User对象）
            // 所以我们不用去手动实现RowMapper接口中的mapRow()方法了，不过需要传入要封装的对象的类名和Class对象，最后queryForObject()方法会返回封装的对象
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
                                                                                                        // 传入参数？                 传入参数？
            // 3. 返回user对象
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
