package cn.itcast.web.servlet;

import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delUserServlet")
public class DelUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取id
        // 因为id是使用get方式传递的，不会出现乱码问题，所以不用设置字符集；而且id是int类型的，不会存在中文乱码问题
        // ${pageContext.request.contextPath}/delUserServlet?${user.id} id是通过user对象获取的，所以id是int类型的
        String id = request.getParameter("id"); // 不过request还是会将获取的参数保存为字符串类型

        // 2. 调用Service删除
        UserService userService = new UserServiceImpl();
        userService.delUser(id);

        // 3. 跳转到userListServlet
        response.sendRedirect(request.getContextPath() + "/userListServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
