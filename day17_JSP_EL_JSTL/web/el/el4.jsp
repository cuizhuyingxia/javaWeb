<%@ page import="cn.itcast.domain.User" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el隐式对象</title>
</head>
<body>
    <%--使用jsp代码动态获取虚拟目录--%>
    <%
        String contextPath = request.getContextPath();
    %>
    <%=contextPath%>

    <%--使用el表达式动态获取虚拟目录--%>
    ${pageContext.request.contextPath}
    <%--pageContext即是内置对象又是隐式对象，可以获取其它8个内置对象，例如获取内置对象request，然后通过request内置对象获取虚拟目录--%>
</body>
</html>
