package cn.itcast.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
Servlet访问路径
 */
//@WebServlet({"/demo4", "/demo44", "/demo444"})  // 为Servlet配置多个访问路径
//@WebServlet({"/user/demo4"})    // 为Servlet配置多层路径
//@WebServlet({"/user/*"})
//@WebServlet({"/*"})
@WebServlet({"*.do"})   // 为Servlet配置带后缀名的路径
public class ServletDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo4...");
    }
}
