<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    <%--引入jstl标签库--%>
<html>
<head>
    <title>if标签</title>
</head>
<body>
    <c:if test="true">
        真
    </c:if>

    <%
        ArrayList<Object> list = new ArrayList<>();
        request.setAttribute("list", list);
    %>

    <c:if test="${not empty list}"> <%--如果list集合不为null或长度不为0--%>
        遍历集合...
    </c:if>

    <%
        request.setAttribute("number", 3);
    %>

    <c:if test="${number % 2 == 0}">    <%--如果number是奇数--%>
        ${number}是偶数
    </c:if>

    <c:if test="${number % 2 != 0}">    <%--如果number是偶数--%>
        ${number}是奇数
    </c:if>


</body>
</html>
