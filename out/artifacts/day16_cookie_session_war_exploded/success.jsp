<%@ page import="cn.itcast.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <%
        User user = (User) request.getSession().getAttribute("user");
    %>
    <h1><%=user.getUsername() + "欢迎您！"%></h1>
</body>
</html>
