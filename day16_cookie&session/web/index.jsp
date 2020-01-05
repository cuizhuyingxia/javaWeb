<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2019/8/24
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

    <%
        System.out.println("hello jsp");
    %>

    <%!
        int i = 3;
    %>

    <%=
        "hello"
    %>

    <%
        String contextPath = request.getContextPath();
        out.write(contextPath);
    %>

    <%
        response.getWriter().write("hello..");
    %>



    <h1>hi jsp</h1>
  </body>
</html>



<%--
JSP概念

    Java Server Pages   java服务端页面

    是一个特殊的特面，即可以定义html标签，又可以定义java代码
    主要功能是用于简化书写
--%>


<%--
JSP原理

    JSP本质上就是一个servlet
        因为jsp页面会被转换为java类，而这个java类继承了HttpServlet，所以JSP本质上就是一个servlet
--%>


<%--
JSP脚本：在JSP中定义java代码

    有3种方式：
        1. <% 代码 %>     该方式定义的代码在jsp页面转换为java类以后的service方法中，service方法中可以定义什么代码，该方式就可以定义什么代码；输出结果会打印到控制台上
        2. <%! 代码 %>    该方式定义的代码在jsp页面转换为java类以后的成员位置上，可以定义成员变量和成员方法以及静态代码块
        3. <%= 代码 %>    该方式定义的代码会输出到页面上，输出语句可以定义什么代码，该方式就可以定义什么代码
--%>


<%--
JSP的内置对象

内置对象：在jsp页面上不需要获取和创建，可以直接使用的对象。（其实是jsp页面在转换为java类以后，在java类中声明了这些对象，所以在jsp页面中就不用再获取和创建对象了，可以直接使用）
JSP一共有9个内置对象
    例如：
        request
        response
        out         字符输出流对象，可以将数据输出到页面上，和response.getWriter()类似
                    response.getWriter()和out.writer()的区别：
                        response.getWriter()输出的数据永远在out.writer()输出的数据前面，
                                因为tomcat服务器在给客户端做出响应时，会先查找response缓冲区的数据，然后再查找out缓冲区中的数据，
                                    所以response.getWriter()输出的数据永远在out.writer()输出的数据前面
--%>