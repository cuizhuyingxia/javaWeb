package cn.itcast.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/*
生成简单的验证码
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 创建一个图片对象
        int width = 100;
        int height = 50;                        // 宽        高                   类型为RGB
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        // 2. 美化图片
        // 获取画笔对象
        Graphics graphics = image.getGraphics();

        // 设置画笔颜色，然后使用该颜色填充背景
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, width, height); // 起点坐标 填充的宽度  填充的高度

        // 设置画笔颜色，然后使用该颜色画边框
        graphics.setColor(Color.BLUE);
        graphics.drawRect(0, 0, width-1, height-1); // 起点坐标 边框的宽度 边框的高度   由于边框本身也会占1个像素，所以需要把边框总长度减1

        // 画验证码
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        // 生成4位随机字符，并将字符画到图片上
        for (int i = 1; i <=4 ; i++) {
            // 生成随机索引
            int index = random.nextInt(str.length());
            // 获取随机字符
            char charAt = str.charAt(index);
            stringBuilder.append(charAt);
            // drawString方法可以将字符串画到图片上
            graphics.drawString(charAt + "", width/5*i, height/2);
                                // 随机字符         随机x坐标       随机y坐标
        }
        String checkCode_session = stringBuilder.toString();
        // 将验证码存储到session中
        request.getSession().setAttribute("checkCode_session", checkCode_session);

        // 画干扰线
        graphics.setColor(Color.GREEN);
        // 生成10条随机干扰线，并将干扰线画到图片上
        for (int i = 0; i <= 2 ; i++) {
            // 生成随机干扰线坐标
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            // drawLine方法可以将线画到图片上
            graphics.drawLine(x1, y1, x2, y2);
        }


        // 3. 将图片输出到页面展示
        // 使用ImageIO的write方法将图片输出到页面
        ImageIO.write(image, "jpg", response.getOutputStream());
                    // 图片对象         图片后缀名       输出流对象
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
