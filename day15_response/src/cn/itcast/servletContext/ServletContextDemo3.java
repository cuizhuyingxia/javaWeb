package cn.itcast.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContextDemo3")
public class ServletContextDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        ServletContext对象的功能

        1. 获取文件的MIME类型

        2. 是一个域对象，可以用来共享数据
            域对象的共享范围为：所有用户      就是说该域中的数据可以供所有用户共享
         */

        // // 设置共享数据
        ServletContext servletContext = this.getServletContext();
        servletContext.setAttribute("msg", "hello");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
