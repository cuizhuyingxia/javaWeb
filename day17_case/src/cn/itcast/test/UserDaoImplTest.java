package cn.itcast.test;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import org.junit.Test;

public class UserDaoImplTest {
    @Test
    public void loginTest() {
        User loginUser = new User();
        loginUser.setUsername("cocozuimei");
        loginUser.setPassword("123");

        UserDao userDao = new UserDaoImpl();
        User user = userDao.login(loginUser);

        System.out.println(user);
    }
}
