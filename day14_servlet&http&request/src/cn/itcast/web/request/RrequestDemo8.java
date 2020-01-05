package cn.itcast.web.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RrequestDemo8")
public class RrequestDemo8 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RrequestDemo8被访问了");

        /*
        请求转发到/RrequestDemo9
         */

        // 存储数据到request域中
        request.setAttribute("msg", "hello");

        // 获取请求转发器对象，需要传入要转发到的资源路径
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/RrequestDemo9");   // 请求转发是在服务器内部进行转发的，所以不需要加虚拟目录
        // 调用forward方法进行转发，需要传入参数request和response
        requestDispatcher.forward(request, response);
        // 链式编程
        //request.getRequestDispatcher("/RrequestDemo8").forward(request, response);



        /*
        request请求转发的特点

        1. 浏览器地址栏路径不会发生变化
        2. 只能转发到当前服务器内部资源中
        3. 即使转发多次，也只会给服务器发送1次请求
         */

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
