package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo3 implements Filter {

    /**
     * 请求被拦截时，会执行，可以执行多次
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("doFilter....");
        chain.doFilter(req, resp);
    }

    /**
     * 服务器启动时，会创建Filter对象，然后调用init方法，只会执行一次。该方法一般用于加载资源
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init....");
    }

    /**
     * 服务器正常关闭时，Filter对象会被销毁，然后调用destroy方法，只会执行一次。该方法一般用于释放资源   （注意：destroy方法执行完毕后，服务器才会关闭）
     */
    public void destroy() {
        System.out.println("destroy....");
    }

}
