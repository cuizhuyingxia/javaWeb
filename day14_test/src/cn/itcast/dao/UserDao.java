package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/*
操作数据库中user表的类
 */
public class UserDao {
    // 创建JdbcTemplate对象
    // 需要传入数据库连接池对象作为参数，然后JdbcTemplate会自动去获取数据库连接对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * 登录方法
     * @param loginUser 只包含用户名和密码
     * @return  返回User对象
     */
    public User login(User loginUser) {
        try {
            // 1. 编写sql
            String sql = "select * from user where username = ? and password = ?";
            // 2. 调用query方法查询
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            // JdbcTemplate会自动帮我们释放资源
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }




}
