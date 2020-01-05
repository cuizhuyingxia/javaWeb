<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="top.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--<%
        System.out.println("hi");
    %>
    <input>--%>

    <!--
    <input>
    -->


    <%
        pageContext.setAttribute("msg", "coco");
    %>

    <%=pageContext.getAttribute("msg")%>
</body>
</html>
