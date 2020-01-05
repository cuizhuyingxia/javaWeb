package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieDemo4")
public class CookieDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        设置Cookie的存活时间

        setMaxAge(int seconds)
                    正数： 将cookie持久化到硬盘，正数值为持久化的时间，持久化时间结束后会自动删除cookie
           （默认值）负数： 将cookie保存到浏览器内存中，当浏览器关闭时，cookie会随之被删除
                    0      删除cookie
         */

        // 创建Cookie对象
        Cookie cookie = new Cookie("msg", "setMaxAge");

        // 设置cookie的存活时间
        //cookie.setMaxAge(30);   // 将cookie持久化到硬盘30秒，30秒后自动删除cookie
        //cookie.setMaxAge(-1);    // 将cookie保存到内存中，当浏览器关闭时，cookie会随之被删除
        cookie.setMaxAge(0);    // 删除cookie
        // 发送Cookie给浏览器
        response.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

