package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(value = "/index.jsp", dispatcherTypes = DispatcherType.REQUEST)  // 请求index.jsp资源时，过滤器才会被执行
//@WebFilter(value = "/index.jsp", dispatcherTypes = DispatcherType.FORWARD)  // 转发到index.jsp资源时，过滤器才会被执行
//@WebFilter(value = "/index.jsp", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})    // 请求或转发到index.jsp资源时，过滤器都会被执行
public class FilterDemo5 implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo5....");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
