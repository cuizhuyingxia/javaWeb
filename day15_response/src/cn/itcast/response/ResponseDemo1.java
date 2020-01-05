package cn.itcast.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
重定向
 */
@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo1....");

        /*// 设置状态码为302
        response.setStatus(302);
        // 设置响应头参数location
        response.setHeader("location", "/day15/ResponseDemo2");*/

        // 测试重定向是否可以使用request共享数据
        request.setAttribute("msg", "hello");
        // 使用sendRedirect方法重定向
        response.sendRedirect("/day15/responseDemo2");  // 重定向是由浏览器客户端发起的请求，所以需要加虚拟目录
        // 可以重定向到其它站点的资源
        //response.sendRedirect("https://baidu.com");

        /*
        forward和redirect的区别？

        forward 请求转发
            1. 浏览器地址栏路径不会发生变化
            2. 只能转发到当前服务器内部资源中
            3. 只会向服务器发送一次请求，可以使用request对象来共享数据

        redirect 重定向
            1. 浏览器地址栏路径会发生变化
            2. 可以访问其它站点（服务器）的资源
            3. 会向服务器发送两次请求，不能使用request对象来共享共享数据
         */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
