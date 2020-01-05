package cn.itcast.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContextDemo1")
public class ServletContextDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        ServletContext对象

        概念：代表整个应用，可以和程序的容器（服务器）通信
         */

        // 获取ServletContext对象

        // 方式一
        ServletContext servletContext1 = request.getServletContext();

        // 方式二
        ServletContext servletContext2 = this.getServletContext();
        // HttpServlet的父类GenericServlet中有getServletContext方法


        System.out.println(servletContext1);    // org.apache.catalina.core.ApplicationContextFacade@5551fb42
        System.out.println(servletContext2);    // org.apache.catalina.core.ApplicationContextFacade@5551fb42

        System.out.println(servletContext1 == servletContext2); // true


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
