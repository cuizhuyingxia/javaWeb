<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>算数运算符</title>
</head>
<body>
    ${3 > 4}
    \${3 > 4}

    <h3>算术运算符</h3>
    ${3 + 4}<br>
    ${3 / 4}<br>
    ${3 div 4}<br>
    ${3 % 4}<br>
    ${3 mod 4}<br>

    <h3>比较运算符</h3>
    ${3 == 4}<br>

    <h3>逻辑运算符</h3>
    ${3 > 4 && 3 < 4}

    <h3>empty运算符</h3>
    <%
        String s = "";
        request.setAttribute("s", s);
    %>
    ${empty s}  <%--判断键是否为null，或者长度为0--%>

    <%
        ArrayList<Object> list = new ArrayList<>(); /*list集合现在虽然不为null，但是长度为0*/
        request.setAttribute("list", list);
    %>
    ${empty list}   <%--判断键是否为null，或者长度为0--%>
    ${not empty list}   <%--判断键是否不为null，或者长度不为0--%>


</body>
</html>
