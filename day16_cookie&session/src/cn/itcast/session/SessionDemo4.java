package cn.itcast.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/sessionDemo4")
public class SessionDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        使用session共享数据
         */

        // 获取session对象
        HttpSession session = request.getSession();
        System.out.println(session);

        // 获取数据
        Object msg = session.getAttribute("msg");
        System.out.println(msg);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}


/*
浏览器第一次请求服务器时，cookie中没有session id

这时候如果服务器要获取session对象，那么获取的就是一个新的session对象，然后服务器会将session对象的session id返回给浏览器，并保存在cookie中
下次浏览器再次请求服务器时，会将cookie中的session id发给服务器，
如果这时服务器再次获取session对象，那么就会获取cookie中的session id对应的session对象，而不是获取一个新的session对象

如果关闭了浏览器，然后再次打开浏览器向服务器发起请求时，因为没有将cookie持久化到硬盘，所以浏览器关闭时，cookie也就销毁了
这个时候cookie中也就没有session id了，如果这个时候服务器要获取session对象，那么获取的则是一个新的session对象

那么怎样可以实现浏览器关闭后再打开，服务器仍然可以获取到之前cookie中的session id对象的session对象呢？
需要将cookie持久化到硬盘中，这样浏览器关闭再打开后，cookie仍然存在，浏览器依然可以将cookie中的session id发送给服务器，
而服务器如果要获取session对象，就会获取cookie中的session id对应的session对象



如果服务器重启了，那么再次获取session对象时，还能获取到之前的session对象吗？
不能。
因为session对象是存储在服务器的内存中的，一旦服务器重启了，那么session对象就被销毁了
那之前的session对象中的数据岂不是会丢失，有解决办法吗？
tomcat会在服务器关闭之前，将session对象序列化到硬盘中，          称为session钝化
然后服务器重启后，tomcat会再将session对象反序列化到内存中         称为session活化
反序列化到内存中session对象虽然地址值和之前的session对象不一样，但是session id以及数据都和之前的session对象一样

如果使用idea中的tomcat，则只能将session对象序列化到硬盘上，而无法将session对象再反序列化到内存中
 */


/*
session什么时候被销毁

1. 服务器关闭时
    session对象是存储在服务器的内存中的，一旦服务器关闭了，那么session对象就被销毁了

2. session对象调用方法invalidate()进行关闭

3. 设置失效时间
    默认失效时间为30分钟
    可以在tomcat服务器下的web.xml中设置失效时间
        D:\apache-tomcat-8.5.31\conf\web.xml
        <session-config>
            <session-timeout>30</session-timeout>
        </session-config>

 */


/*
session特点

1. session用于一次会话中多次请求之间共享数据，并将数据存储在服务器中

2. session可以存储任意类型，任意大小的数据

session于cookie的区别
    1. session存储在服务器，cookie存储在客户端
    2. session没有数据大小限制，cookie则有大小限制
    3. session存储数据比较安全（因为存储在服务器中，所以安全性高），cookie不太安全
 */