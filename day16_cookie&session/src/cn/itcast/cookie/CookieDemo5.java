package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieDemo5")
public class CookieDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        存储中文数据到cookie中

        在tomcat8之前，不能直接存储中文数据到cookie中，需要先将中文数据进行转码，一般采用URL编码
        在tomcat8之后，可以直接存储中文数据到cookie中
         */

        // 创建Cookie对象
        Cookie cookie = new Cookie("msg", "你好");    // 当前使用的版本为tomcat8.5.3

        // 发送Cookie给浏览器
        response.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
