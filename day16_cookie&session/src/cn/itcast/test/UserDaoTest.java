package cn.itcast.test;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("coco");
        loginUser.setPassword("1231");

        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);

        System.out.println(user);
    }

}
