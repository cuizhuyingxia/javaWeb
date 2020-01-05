package cn.itcast.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//@WebListener
public class ServletContextLoadListener implements ServletContextListener {

    /**
     * 监听ServletContext对象是否被创建了，服务器启动时会调用该方法    // 服务器启动时，会创建ServletContext对象
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        /*
        监听器contextInitialized的作用：

        contextInitialized监听器用来监听ServletContext对象是否被创建了，而ServletContext对象会在服务器启动时创建，
        所以我们可以在监听器中加载一些文件（通常是全局类型的文件），这样服务器一启动，文件就会被自动加载
         */

        // 1. 获取ServletContext对象
        ServletContext servletContext = servletContextEvent.getServletContext();

        // 2. 加载文件
        // 获取文件在web目录下的访问路径
        // 我们可以将文件在web目录下的访问路径配置在web.xml的初始化参数中，然后通过getInitParameter方法获取
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println(contextConfigLocation);  // /WEB-INF/classes/applicationContext.xml

        // 获取文件在服务器中的真实路径
        String realPath = servletContext.getRealPath(contextConfigLocation);
        System.out.println(realPath);   // C:\IdeaProjects\JavaWeb\out\artifacts\day19_filter_listener_war_exploded\WEB-INF\classes\applicationContext.xml

        // 加载文件进内存
        try {
            FileInputStream fileInputStream = new FileInputStream(realPath);
            // 如果能打印出输入流对象，说明文件被成功加载了
            System.out.println(fileInputStream);    // java.io.FileInputStream@449ab106
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("ServletContext对象被创建了.....");
    }

    /**
     * 监听ServletContext对象是否被销毁了，服务器正常关闭时会调用该方法    // 服务器正常关闭时，会销毁ServletContext对象
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext对象被销毁了.....");
    }
}
