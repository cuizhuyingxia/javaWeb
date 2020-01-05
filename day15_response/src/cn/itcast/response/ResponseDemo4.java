package cn.itcast.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
字符输出流
 */
@WebServlet("/responseDemo4")
public class ResponseDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        字符流乱码问题

        为什么会出现乱码？
            编码和解码使用的字符集不一样

            浏览器使用的字符集，和本地操作系统有关系，如果你是windows操作系统，那么浏览器默认的字符集为GBK或gb2312
            在服务器中，如果你的字符流对象是new出来的，那么字符流对象的字符集，也会和操作系统的字符集一样，如果是windows，那么字符集为GBK或gb2312
            如果你的字符流对象是获取的，例如：使用Request和Response获取字符流对象，因为Request和Response对象是由Tomcat创建的，
            所以我们在使用Request和Response对象获取字符流对象时，字符流对象的字符集会和Tomcat所使用的字符集一样，为  ISO-8859-1，ISO-8859-1字符集不支持中文

            因此：当浏览器在使用GBK字符集去解码ISO-8859-1字符集时就会出现乱码问题

         */

        // 在获取流对象之前，设置流的字符集为GBK
        //response.setCharacterEncoding("GBK");

        // 告诉浏览器，服务器使用的字符集，以及建议浏览器也使用相同的字符集
        response.setContentType("text/html;charset=utf-8");

        // 获取字符输出流
        PrintWriter printWriter = response.getWriter();
        // 输出数据
        printWriter.write("你好hello");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
