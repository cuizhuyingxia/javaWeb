package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")  /*过滤器的先后顺序：根据过滤器的类名进行字符串比较，谁的值小，谁先执行*/
public class FilterDemo6 implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo6执行了");

        // 放行
        chain.doFilter(req, resp);

        System.out.println("FilterDemo6回来了");
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
