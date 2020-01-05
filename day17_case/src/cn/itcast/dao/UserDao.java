package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 户操作的Dao
 */
public interface UserDao {
    /**
     * 查询所有的用户信息
     * @return
     */
    List<User> findAll();

    /**
     * 登录方法，查询用户名和密码是否正确
     * @param loginUser
     * @return
     */
    User login(User loginUser);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void delUser(int id);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页条件查询
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);



}
