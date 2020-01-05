package cn.itcast.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取请求参数：filename
        request.setCharacterEncoding("utf-8");
        String filename = request.getParameter("filename");

        // 2. 使用字节输入流加载文件进内存
        // 2.1 找到文件在服务器的路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/" + filename);
        // 2.2 使用字节流关联
        FileInputStream fileInputStream = new FileInputStream(realPath);

        // 3. 设置response的响应头
        // 获取文件的MIME类型
        String mimeType = servletContext.getMimeType(filename);
        // 设置响应头：content-type   类型
        response.setHeader("content-type", mimeType);

        // 解决中文文件名不能正常显示问题
        // 获取请求头user-agent
        String agent = request.getHeader("user-agent");
        // 使用工具类DownLoadUtils中的getFileName方法对文件名进行编码
        String fileName = DownLoadUtils.getFileName(agent, filename);

        // 设置响应头：content-disposition    打开方式
        response.setHeader("content-disposition", "attachment;filename=" + fileName);

        // 4. 将字节输入流中的数据写出到response输出流中
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024 * 8];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }

        // 关闭字节输入流
        fileInputStream.close();
        // response输出流就不用关了，因为响应结束后，response输出流会自动被关闭

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
