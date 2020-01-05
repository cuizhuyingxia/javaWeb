<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach begin="1" end="0" var="i">   <%--如果end值为0，那么循环体不会被执行--%>
        ${i}hehe
    </c:forEach>
    hello
</body>
</html>
