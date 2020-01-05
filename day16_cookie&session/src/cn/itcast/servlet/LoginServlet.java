package cn.itcast.servlet;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置request字符集（编码）
        request.setCharacterEncoding("utf-8");

        // 2. 获取所有请求参数，然后封装到User对象中
        Map<String, String[]> parameterMap = request.getParameterMap();
        User loginUser = new User();
        try {
            // BeanUtils类的populate方法可以将map集合的键值对信息封装到JavaBean对象中
            // 这里我们将parameterMap结合中的键值对信息封装到loginUser对象中
            BeanUtils.populate(loginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 3. 判断验证码是否正确
        // 从session中获取生成的验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        // 获取用户输入的验证码
        String checkCode = parameterMap.get("checkCode")[0];
        // 删除session中存储的验证码
        session.removeAttribute("checkCode_session");
        // 判断用户输入的验证码和生成的验证码是否一致
        if (checkCode_session != null && checkCode_session.equalsIgnoreCase(checkCode)) {    // 忽略大小写

            // 创建UserDao对象，调用login方法，并判断是否登录成功
            UserDao userDao = new UserDao();
            User user = userDao.login(loginUser);
            if (user == null) {
                // 登录失败
                // 存储错误提示信息到request中
                request.setAttribute("login_err", "用户名或密码错误");
                // 转发到登录页面
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {    // 登录成功
                // 存储用户信息
                session.setAttribute("user", user);
                // 重定向到首页
                response.sendRedirect(request.getContextPath() + "/success.jsp");
            }
        } else {
            // 验证码不一致
            // 存储错误提示信息到request中
            request.setAttribute("checkCode_err", "验证码错误");
            // 转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
