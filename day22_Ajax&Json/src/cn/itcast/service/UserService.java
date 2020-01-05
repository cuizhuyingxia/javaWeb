package cn.itcast.service;

import cn.itcast.domain.User;

public interface UserService {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}
