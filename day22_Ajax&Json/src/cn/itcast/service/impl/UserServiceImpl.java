package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

public class UserServiceImpl implements UserService {
    // 创建UserDao对象
    private UserDao dao = new UserDaoImpl();

    @Override
    public User findUserByUsername(String username) {
        return dao.findUserByUsername(username);
    }
}
