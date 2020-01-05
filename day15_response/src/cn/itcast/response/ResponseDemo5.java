package cn.itcast.response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
字节输出流
 */
@WebServlet("/responseDemo5")
public class ResponseDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        字节流乱码问题

            字节流不管是new的还是获取的，默认的字符集都是GBK（有待验证）
            而当前使用的是windows操作系统，所以浏览器的字符集也是GBK，所以不会出现乱码问题

            不过如果我们修改了字节流的字符集为utf-8，那么还是会出现乱码问题的，因为浏览器是GBK，服务器是utf-8，字符集不一致

         */

        // 告诉浏览器，服务器使用的字符集，以及建议浏览器也使用相同的字符集
        response.setContentType("text/html;charset=utf-8");

        // 获取字节输出流，默认字符集为GBk
        ServletOutputStream outputStream = response.getOutputStream();
        // 输出数据
        //outputStream.write("你好啊hello".getBytes());    // 不会出现乱码，因为字节流的字符集默认为GBK，而浏览器也是GBK

        // 修改字符集为utf-8
        outputStream.write("你好啊hello".getBytes("utf-8"));   // 也不会出现乱码，因为已经告诉浏览器使用utf-8进行解码
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
