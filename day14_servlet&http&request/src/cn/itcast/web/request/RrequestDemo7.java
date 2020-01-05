package cn.itcast.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet("/RrequestDemo7")
public class RrequestDemo7 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        解决中文乱码问题
        如果是get请求方式，在获取数据时，不会出现中文乱码问题，因为tomcat8以后，修复了get方式中文乱码问题
        而post请求方式，在获取数据时，会出现中文乱码问题，因为post方式是使用流获取数据的，所以需要设置编码为utf-8
         */

        // 设置编码为utf-8
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        System.out.println(username);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
