package cn.itcast.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/RrequestDemo5")
public class RrequestDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        获取请求体数据（只有post请求方式才有请求体，请求体中封装了post请求的参数）
         */

        // 获取流对象
        BufferedReader reader = request.getReader();
        // 从流中获取数据
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
