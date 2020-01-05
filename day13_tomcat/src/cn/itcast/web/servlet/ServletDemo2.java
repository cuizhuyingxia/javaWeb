package cn.itcast.web.servlet;

import javax.servlet.*;
import java.io.IOException;

/*
Servlet的方法
 */
public class ServletDemo2 implements Servlet {
    /*
    初始化方法
    在Servlet被创建时执行，只会执行一次
    因为Servlet的init方法只会被执行一次，说明一个Servlet在内存中只存在一个对象，所以Servlet是单例的
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init...");
    }

    /*
    获取ServletConfig对象，也就是Servlet的配置对象
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /*
    提供服务方法
    每一次Servlet被访问时执行
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service...");
    }

    /*
    获取Servlet的一些信息：如版本、作者等等
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /*
    销毁方法
    在服务器正常关闭时执行（注意：destroy方法执行完毕后，服务器才会关闭）
     */
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}
