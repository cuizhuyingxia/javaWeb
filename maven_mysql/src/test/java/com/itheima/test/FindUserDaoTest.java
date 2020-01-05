package com.itheima.test;

import com.itheima.dao.impl.FindUserDaoImpl;
import com.itheima.domain.User;
import org.junit.Test;

import java.util.List;

public class FindUserDaoTest {
    @Test
    public void testFindUser() {
        FindUserDaoImpl findUserDao = new FindUserDaoImpl();
        List<User> users = findUserDao.findUser();
        for (User user : users) {
            System.out.println(user.getName());
        }
    }
}
