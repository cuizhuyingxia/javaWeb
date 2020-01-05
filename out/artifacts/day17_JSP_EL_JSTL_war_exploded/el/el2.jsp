<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取域中存储的值</title>
</head>
<body>
    <%--向域对象中存储数据--%>
    <%
        application.setAttribute("name", "mumu");
        pageContext.setAttribute("name", "coco");
        request.setAttribute("gender", "female");
        session.setAttribute("age", "18");
        application.setAttribute("faceValue", "100");
    %>

    <%--从指定的域中获取指定键的值--%>
    ${pageScope.name}
    ${requestScope.gender}
    ${sessionScope.age}
    ${applicationScope.faceValue}

    <%--依次从最小的域中查找是否有该键对应的值，直到找到为止--%>
    ${name} <%--coco--%>

    <%--
    域对象的大小关系：pageContext < request < session < application
    --%>
</body>
</html>
