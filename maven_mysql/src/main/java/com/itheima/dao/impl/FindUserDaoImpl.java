package com.itheima.dao.impl;

import com.itheima.dao.FindUserDao;
import com.itheima.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FindUserDaoImpl implements FindUserDao {
    public List<User> findUser(){
        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;
        List<User> list = null;
        try {
            // 1. 加载mysql驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 获取连接对象
            connection = DriverManager.getConnection("jdbc:mysql:///day17", "root", "root");

            // 3. 定义sql语句
            String sql = "select * from user";

            // 4. 获取执行sql语句的对象PreparedStatement，需要传入sql作为参数
            preparedStatement = connection.prepareStatement(sql);

            // 5. 执行sql语句，不需要传入sql作为参数
            resultSet = preparedStatement.executeQuery();

            // 6. 将结果集转换为List集合
            list = new ArrayList<User>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                list.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 7. 关闭资源
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
