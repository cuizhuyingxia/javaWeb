package cn.itcast.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RrequestDemo4")
public class RrequestDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        获取请求头referer的值
         */

        String referer = request.getHeader("referer");
        System.out.println(referer);

        // 防盗链
        if (referer != null) {
            if (referer.contains("/day14")) {
                // 正常访问
                //System.out.println("播放电影");
                response.setContentType("text/html;charset=utf-8"); // 设置中文
                response.getWriter().write("播放电影"); // 通过response对象获取字符输出流，写出数据
            } else {
                // 盗链
                //System.out.println("想看电影吗？来优酷吧");
                response.setContentType("text/html;charset=utf-8"); // 设置中文
                response.getWriter().write("想看电影吗？来优酷吧"); // 获取response对象获取字符输出流，写出数据

            }
        }
    }
}
