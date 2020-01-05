package cn.itcast.web.servlet;

import cn.itcast.domain.Province;
import cn.itcast.service.ProvinceService;
import cn.itcast.service.impl.ProvinceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findProvinceServlet")
public class FindProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*// 1. 调用Service获取省份
        ProvinceService service = new ProvinceServiceImpl();
        List<Province> list = service.findAll();
        
        // 2. 将list集合转换为JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);*/

        // 1. 调用Service获取省份
        ProvinceService service = new ProvinceServiceImpl();
        String json = service.findAllJson();
        System.out.println(json);

        // 2. 响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
