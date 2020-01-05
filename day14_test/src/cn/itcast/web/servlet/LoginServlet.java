package cn.itcast.web.servlet;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码为utf-8
        request.setCharacterEncoding("utf-8");

        /*// 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 封装到User对象中
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);*/


        // 获取所有请求参数，然后封装到User对象中
        Map<String, String[]> parameterMap = request.getParameterMap();
        User loginUser = new User();
        try {
            // BeanUtils类的populate方法可以将map集合的键值对信息封装到JavaBean对象中
            // 这里我们将parameterMap集合中的键值对信息封装到loginUser对象中
            BeanUtils.populate(loginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 创建UserDao对象，调用login方法，并判断是否登录成功
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        if (user == null) {
            // 登录失败
            request.getRequestDispatcher("/failServlet").forward(request, response);
        } else {
            // 登录成功
            // 将user对象存储到request域中
            request.setAttribute("user", user);
            request.getRequestDispatcher("/successServlet").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
