package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
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
        // 1. 设置字符集
        request.setCharacterEncoding("utf-8");

        // 2. 获取数据
        // 获取验证码
        String verifycode = request.getParameter("verifycode");
        // 获取所有数据，并封装到map集合中
        Map<String, String[]> parameterMap = request.getParameterMap();

        // 3. 将获取到的数据封装user对象
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 4. 验证码校验
        // 获取session中的验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");    // 删除session中的验证码，确保验证码只能一次性使用
        if (checkcode_server != null && checkcode_server.equalsIgnoreCase(verifycode)) {
            // 如果验证码一致
            // 5. 调用service查询
            UserService userService = new UserServiceImpl();
            User user = userService.login(loginUser);

            // 6. 判断是否登录成功
            if (user != null) {
                // 登录成功
                // 存储user到session中
                session.setAttribute("user", user);
                // 重定向到index.jsp页面
                response.sendRedirect(request.getContextPath() + "/index.jsp");

            } else {
                // 登录失败
                // 存储错误提示信息到request域中
                request.setAttribute("login_msg", "用户名或密码错误");
                // 转发到login.jsp页面
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            // 如果验证码不一致
            // 存储错误提示信息到request域中
            request.setAttribute("login_msg", "验证码错误");
            // 转发到login.jsp页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
