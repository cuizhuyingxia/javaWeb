package cn.itcast.test;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin() {
        // 创建User对象，并设置username和password
        User loginUser = new User();
        loginUser.setUsername("cococ");
        loginUser.setPassword("123");

        // 创建UserDao对象，传入User对象，测试
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        System.out.println(user);
    }
}
