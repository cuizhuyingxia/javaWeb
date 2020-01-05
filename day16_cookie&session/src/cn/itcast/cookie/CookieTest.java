package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        案例

        在服务器的Servlet中判断是否有一个名为lastName的cookie
            1. 如果有，说明不是第一次访问
                设置响应数据：欢迎回来，您上次访问的时间为：时间
                更新lastName的值为当前时间

            2. 如果没有，则是第一次访问
                设置响应数据：您好，欢迎您首次访问
                设置lastName的值为当前时间
         */

        // 设置字符流已经浏览器的字符集都为utf-8
        response.setContentType("text/html;charset=utf-8");

        // 设置flag，默认为false，即首次访问
        boolean flag = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0)
        for (Cookie cookie : cookies) {
            // 获取cookie名称
            String name = cookie.getName();
            // 判断cookie名称是否为lastName，如果是，说明不是第一次访问
            if ("lastName".equals(name)) {
                // 设置flag为true，即不是第一次访问
                flag = true;

                // 获取lastName的值，即上次访问的时间
                String lastNameValue = cookie.getValue();

                // 更新lastName的值为当前时间
                // 设置时间格式
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String str_date = simpleDateFormat.format(date);
                // 为防止特殊字符导致的乱码问题，所以先使用URL进行编码
                String encode_str_date = URLEncoder.encode(str_date, "utf-8");
                // 设置lastName的值
                cookie.setValue(encode_str_date);
                // 设置cookie的存活时间
                cookie.setMaxAge(60 * 60 * 24);
                // 发送cookie
                response.addCookie(cookie);

                // 设置响应数据：欢迎回来，您上次访问的时间为：时间
                // 为防止特殊字符导致的乱码问题，所以先使用URL进行解码
                String decode_lastNameValue = URLDecoder.decode(lastNameValue, "utf-8");
                response.getWriter().write("<h1>欢迎回来，您上次访问时间为："+decode_lastNameValue+"</h1>");

                // 如果已经获取到lastName，则跳出循环
                break;
            }
        }

        // 如果是第一次访问
        if (cookies == null || cookies.length == 0 || !flag) {
            // 设置lastName的值为当前时间
            // 设置时间格式
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = simpleDateFormat.format(date);
            // 为防止特殊字符导致的乱码问题，所以先使用URL进行编码
            String encode_str_date = URLEncoder.encode(str_date, "utf-8");
            // 设置lastName的值
            Cookie cookie = new Cookie("lastName", encode_str_date);
            // 设置cookie的存活时间
            cookie.setMaxAge(60 * 60 * 24);
            // 发送cookie
            response.addCookie(cookie);

            // 设置响应数据：您好，欢迎您首次访问
            response.getWriter().write("<h1>您好，欢迎您首次访问</h1>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
