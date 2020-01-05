package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    // 创建JdbcTemplate对象
    // 需要传入数据库连接池对象作为参数，然后JdbcTemplate会自动去获取数据库连接对象，也会自动帮我们释放资源
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        // 使用JDBC操作数据库
        // 1. 定义sql语句
        String sql = "select * from user";

        // 2. 查询，并将返回的User对象装载到集合中
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public User login(User loginUser) {
        try {
            // 1. 定义sql语句
            String sql = "select * from user where username = ? and password = ?";
            // 2. 查询，并返回User对象
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        // 1. 定义sql
        String sql = "insert into user values(null, ?, ?, ?, ?, ?, ?, null, null)";
        // 2. 添加
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void delUser(int id) {
        // 1. 定义Sql
        String sql = "delete from user where id = ?";
        // 2. 删除
        template.update(sql, id);
    }

    @Override
    public User findUserById(int id) {
        // 1. 定义sql
        String sql = "select * from user where id = ?";
        // 2. 查询，并返回User对象
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);

        return user;
    }

    @Override
    public void updateUser(User user) {
        // 1. 定义sql
        String sql = "update user set name = ?, gender = ?, age = ?, address = ?, qq = ?, email = ? where id = ?";
        // 2. 修改
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        // 定义模板初始化sql
        String sql = "select count(*) from user where 1 = 1";

        // 拼接sql
        StringBuilder stringBuilder = new StringBuilder(sql);

        // 遍历map，筛选出查询条件参数
        Set<String> keySet = condition.keySet();
        // 定义存储sql参数的集合
        ArrayList<Object> params = new ArrayList<>();
        for (String key : keySet) {
            // 排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            // 获取value
            String value = condition.get(key)[0];
            // 如果value不为空并且不是空字符串
            if (value != null && !"".equals(value)) {
                // 拼接sql
                stringBuilder.append(" and "+key+" like ? ");
                // 添加参数到集合中
                params.add("%" + value + "%");
            }
        }
        System.out.println(stringBuilder.toString());
        System.out.println(params);
        Integer totalCount = template.queryForObject(stringBuilder.toString(), Integer.class, params.toArray());
        return totalCount;
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1";

        // 拼接sql
        StringBuilder stringBuilder = new StringBuilder(sql);

        // 遍历map，筛选出查询条件参数
        Set<String> keySet = condition.keySet();
        // 定义存储sql参数的集合
        ArrayList<Object> params = new ArrayList<>();
        for (String key : keySet) {
            // 排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            // 根据value
            String value = condition.get(key)[0];
            // 如果value不为空并且不是空字符串
            if (value != null && !"".equals(value)) {
                // 拼接sql
                stringBuilder.append(" and "+key+" like ? ");
                // 添加参数到集合中
                params.add("%" + value + "%");
            }
        }

        // 添加分页查询
        stringBuilder.append(" limit ? , ? ");
        params.add(start);
        params.add(rows);
        System.out.println(stringBuilder.toString());
        System.out.println(params);
        List<User> users = template.query(stringBuilder.toString(), new BeanPropertyRowMapper<User>(User.class), params.toArray());
        return users;
    }
}
