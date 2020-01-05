package cn.itcast.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 0. 将父接口转为子接口（因为ServletRequest中的方法不全，例如就没有getRequestURI()方法）
        HttpServletRequest request = (HttpServletRequest) req;

        // 1. 获取请求资源路径
        String uri = request.getRequestURI();

        // 2. 判断是否包含登录相关的资源路径，注意要排除掉验证码 css js 图片等资源
        if (uri.contains("login.jsp") || uri.contains("/loginServlet") || uri.contains("/checkCodeServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/")) {
            // 如果包含，说明用户想去登录，则放行
            chain.doFilter(req, resp);
        } else {
            // 如果不包含，则拦截，然后验证用户是否登录
            // 3. 从session中获取user对象
            Object user = request.getSession().getAttribute("user");
            // 4. 如果user对象不为null，说明用户已经登录，则放行
            if (user != null) {
                chain.doFilter(req, resp);
            } else {
                // 5. 如果user对象为null，说明用户没有登录，则跳转到登录页面，并提示用户登录
                request.setAttribute("login_msg", "请登录");
                request.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
