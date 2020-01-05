<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    <%--引入jstl标签库--%>
<html>
<head>
    <title>choose标签</title>
</head>
<body>
    <%--
    完成重复的操作
        for (int i = 1; i <= 10; i++) {
                System.out.println(i);
            }

        属性：
            begin   开始值     相当于i = 1
            end     结束值     相当于i <= 10
            var     临时变量   相当于int i
            step    步长       相当于i++

            varStatus   循环的状态对象
                index      在完成重复的操作时，index的值和begin的值一样；如果是遍历容器，那么index的值为容器中元素的索引。
                count       循环的次数
    --%>
    <c:forEach begin="1" end="10" var="i" step="1" varStatus="status">
        ${i} ${status.index} ${status.count}<br>
    </c:forEach>



    <%
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        request.setAttribute("list", list);
    %>

    <%--
    遍历容器
    for (String str : list) {
            System.out.println(str);
        }

        属性：
            items   容器对象                相当于list
            var     容器中元素的临时变量      相当于String str

            varStatus   循环的状态对象
                index      在完成重复的操作时，index的值和begin的值一样；如果是遍历容器，那么index的值为容器中元素的索引。
                count      循环的次数

    --%>
    <c:forEach items="${list}" var="str" varStatus="status">
        ${status.index} ${status.count} ${str}<br>
    </c:forEach>


</body>
</html>
