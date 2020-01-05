package cn.itcast.web.servlet;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 0. 设置字符集
        request.setCharacterEncoding("utf-8");

        // 1. 获取参数
        String currentPage = request.getParameter("currentPage");   // 当前页
        String rows = request.getParameter("rows"); // 每页显示的记录数
        // 如果参数为空，或者为空字符串，则默认显示第一页的数据   因为用户在index.jsp页面点击查询，跳转到list.jsp页面时，不需要传入参数，默认应该显示第一页的数据
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "5";
        }

        // 获取复杂查询条件参数
        Map<String, String[]> condition = request.getParameterMap();


        // 2. 调用Service查询
        UserService userService = new UserServiceImpl();
        PageBean<User> pageBean = userService.findUserByPage(currentPage, rows, condition);
        System.out.println(pageBean.getCurrentPage());
        System.out.println(pageBean.getTotalPage());
        System.out.println(pageBean.getTotalCount());
        // 3. 将返回的PageBean存入request域中
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("condition", condition);

        // 4. 转发到list.jsp页面展示
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
