<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.itcast.domain.User" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>
    <%
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("coco", 18, new Date()));
        list.add(new User("mumu", 19, new Date()));
        list.add(new User("tutu", 20, new Date()));
        request.setAttribute("list", list);
    %>

    <table border="1" width="500" align="center">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>生日</th>
        </tr>
        <%--数据行--%>
        <c:forEach items="${list}" var="user" varStatus="status">
            <c:if test="${status.count % 2 != 0}">
                <tr bgcolor="red">
                    <td>${status.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.birthdayStr}</td>
                </tr>
            </c:if>

            <c:if test="${status.count % 2 == 0}">
                <tr bgcolor="green">
                    <td>${status.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.birthdayStr}</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</body>
</html>
