package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserServiceImpl implements UserService {
    // 创建UserDaoImpl对象
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User loginUser) {
        return dao.login(loginUser);
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void delUser(String id) {
        dao.delUser(Integer.parseInt(id));  // 将id转换为int类型，因为数据库中的id是int类型
    }

    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public void delSelectUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                dao.delUser(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        // 将当前页和每页显示的记录数转换为整型
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        // 1. 创建PageBean对象
        PageBean<User> pageBean = new PageBean<>();

        // 2. 调用Dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pageBean.setTotalCount(totalCount);

        // 3. 计算总页码数
        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pageBean.setTotalPage(totalPage);

        // 如果当前页小于1，则将currentPage置为1    因为如果用户在第1页时，点击上一页按钮，那么页码就会小于1
        if (currentPage < 1) {
            currentPage = 1;
        }
        // 如果当前页大于总页码，则将currentPage置为totalPage    因为如果用户在最后一页时，点击下一页按钮，那么页码就会大于总页码数
        /*
        因为在使用条件查询时，比如查询姓名为张三的记录，而数据库中没有张三的记录，所以这时总记录数就会为0，而currentPage的默认值为1，
        就会出现currentPage > totalPage的情况，如果直接将currentPage = totalPage;    则currentPage会等于0
        然后再计算每页的起始索引时，就会出现负数的情况，(0 - 1) * rows = -5   这明显不是我们想要的，因为数据库的索引是不会有负数的，所以页面就会报错
         */
        // 所以，应该先判断totalPage是否为0
        if (totalPage != 0) {
            // 如果不为0，再判断当前页是否大于总页数，如果大于总页数，则将总页数的值赋给当前页，即显示最后一页的数据
            if (currentPage > totalPage) {
                currentPage = totalPage;
            }
        }// 如果为0，则不进行其它操作，如果如果总页数为0，说明没有查到数据，即使用户一直点第一页也没关系
        // 因为从数据库中查询不到数据，页面会一致显示空白，即使用户一直点下一页，是当前页的数据变为1000，也没关系，反正查不到数据

        // 4. 设置currentPage和rows属性
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);

        // 5. 计算出每页的起始索引    （当前页 - 1）* rows
        int start = (currentPage - 1) * rows;

        // 6. 调用Dao查询每页的数据
        List<User> users = dao.findByPage(start, rows, condition);
        pageBean.setList(users);

        // 7. 返回pageBean
        return pageBean;
    }
}
