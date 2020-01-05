package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo2 implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("用于增强request对象的请求消息");

        // 放行
        chain.doFilter(req, resp);

        System.out.println("用于增强response对象的响应消息");
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
