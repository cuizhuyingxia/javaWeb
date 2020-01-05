package cn.itcast.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

//@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 1. 创建代理对象，增强request对象的getParameter方法
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 增强getParameter方法
                // 判断是否是getParameter方法
                if (method.getName().equals("getParameter")) {
                    // 增强返回值
                    // 获取返回值
                    String value = (String) method.invoke(req, args);
                    if (value != null) {
                        for (String s : list) {
                            if (value.contains(s)) {    // 如果返回值中包含敏感词汇
                                value = value.replaceAll(s, "xxx"); // 则将敏感词汇替换为xxx
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req, args);
            }
        });

        // 2. 放行
        chain.doFilter(proxy_req, resp);    // 传入代理对象
    }

    // 用于存储敏感词汇的集合
    private List<String> list = new ArrayList<String>();
    public void init(FilterConfig config) throws ServletException {
        // 读取敏感词汇文件，并将敏感词汇添加到集合中
        try {
            // 0. 获取ServletContext对象
            ServletContext servletContext = config.getServletContext();
            // 1. 获取敏感词汇文件的真实路径
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");

            // 2. 读取文件
            BufferedReader bufferedReader = new BufferedReader(new FileReader(realPath));
            // 定义字符串，用来保存读取的一行数据
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // 3. 将每一行数据添加到集合中
                list.add(line);
            }

            bufferedReader.close();
            System.out.println(list);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

}
