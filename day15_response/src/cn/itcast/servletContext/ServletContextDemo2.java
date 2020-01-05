package cn.itcast.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContextDemo2")
public class ServletContextDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        ServletContext对象的功能

        1. 获取文件的MIME类型
         */

        // 获取ServletContext对象
        ServletContext servletContext = this.getServletContext();
        // 定义文件名称
        String filename = "a.jpg";
        // 获取文件的MIME类型
        String mimeType = servletContext.getMimeType(filename);
        System.out.println(mimeType);   // image/jpeg

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
