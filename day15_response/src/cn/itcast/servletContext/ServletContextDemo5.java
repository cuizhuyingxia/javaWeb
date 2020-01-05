package cn.itcast.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

@WebServlet("/servletContextDemo5")
public class ServletContextDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        ServletContext对象的功能

        1. 获取文件的MIME类型

        2. 是一个域对象，可以用来共享数据
            域对象的共享范围为：所有用户      就是说该域中的数据可以供所有用户共享

        3. 获取文件的真实（服务器）路径
         */

        ServletContext servletContext = this.getServletContext();
        String realPath1 = servletContext.getRealPath("/b.txt"); // web目录下的资源访问
        System.out.println(realPath1);   // C:\IdeaProjects\JavaWeb\out\artifacts\day15_response_war_exploded\b.txt

        String realPath2 = servletContext.getRealPath("/WEB-INF/c.txt");    // WEB-INF目录下的资源访问
        System.out.println(realPath2);  // C:\IdeaProjects\JavaWeb\out\artifacts\day15_response_war_exploded\WEB-INF\c.txt

        String realPath3 = servletContext.getRealPath("/WEB-INF/classes/a.txt");    // src目录下的资源访问
        System.out.println(realPath3);  // C:\IdeaProjects\JavaWeb\out\artifacts\day15_response_war_exploded\WEB-INF\classes\a.txt


        // 使用类加载器获取src目录下的资源
        String path = ServletContextDemo5.class.getClassLoader().getResource("a.txt").getPath();
        System.out.println(path);   // /C:/IdeaProjects/JavaWeb/out/artifacts/day15_response_war_exploded/WEB-INF/classes/a.txt

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
