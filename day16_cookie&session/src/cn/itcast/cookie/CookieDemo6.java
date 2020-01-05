package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieDemo6")
public class CookieDemo6 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        共享cookie

            当前服务器下部署的所有项目如何共享cookie信息？
                取决于setPath(String path)
                    默认情况下path的值为当前虚拟目录，也就是说cookie只能在当前项目中共享
                    如果设置path的值为/    则当前服务器下部署的所有项目都可以共享cookie信息

            不同服务器之间如何共享cookie？
                取决于setDomain(String path)
                    例如现有百度的两个服务器，域名分别为：tieba.baidu.com  news.baidu.com
                    那么如何使这两个服务器之间共享cookie呢？
                        需要在两个服务器中都设置setDomain(".baidu.com")
                            就是说，设置两个服务器的一级域名相同，这样两个服务器之间就可以共享cookie

         */

        // 创建Cookie对象
        Cookie cookie = new Cookie("msg", "你好");    // 当前使用的版本为tomcat8.5.3

        // 设置path，使当前服务器下部署的所有项目可以共享cookie信息
        cookie.setPath("/");

        // 发送Cookie给浏览器
        response.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
