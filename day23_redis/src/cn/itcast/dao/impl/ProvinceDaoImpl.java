package cn.itcast.dao.impl;

import cn.itcast.dao.ProvinceDao;
import cn.itcast.domain.Province;
import cn.itcast.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    // 创建JdbcTemplate对象
    // 需要传入数据库连接池对象作为参数，然后JdbcTemplate会自动去获取数据库连接对象，也会自动帮我们释放资源
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        try {
            // 1. 定义sql
            String sql = "select * from province";
            // 2. 查询
            List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
