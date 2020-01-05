package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/index.jsp")    /*访问index.jsp资源时，过滤器会被执行*/
//@WebFilter("/user/*")       /*访问user目录下的资源源时，过滤器会被执行*/
//@WebFilter("*.jsp")         /*访问后缀名为.jsp的资源时，过滤器会被执行*/
//@WebFilter("/*")            /*访问任何资源时，过滤器都会被执行*/
public class FilterDemo4 implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("hello");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
