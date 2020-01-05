package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取用户名
        String username = request.getParameter("username");

        // 2. 创建map集合
        Map<String, Object> map = new HashMap<>();

        // 3. 设置响应数据的mime类型为json格式，字符集为utf-8
        response.setContentType("application/json;charset=utf-8");

        // 4. 调用Service查询用户名是否存在
        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUsername(username);
        if (user != null) {
            // 存在
            map.put("userExsit", true);
            map.put("msg", "用户名已存在");
        } else {
            // 不存在
            map.put("userExsit", false);
            map.put("msg", "用户名可用");
        }

        // 5. 响应数据
        // 将map对象转换为JSON字符串，并发送给客户端
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
