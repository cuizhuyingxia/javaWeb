<%@ page import="cn.itcast.domain.User" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取域中存储的对象</title>
</head>
<body>
    <%
        User user = new User();
        user.setName("莲花");
        user.setAge(18);
        user.setBirthday(new Date());
        request.setAttribute("user", user);

        ArrayList<Object> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add(user);
        request.setAttribute("list", list);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("score", 100);
        map.put("hobby", "book");
        map.put("user", user);
        request.setAttribute("map", map);
    %>

    <h3>获取域中存储的对象的值</h3>
    ${requestScope.user.name}<br>
    ${requestScope.user.age}<br>
    ${requestScope.user.birthday}<br>
    ${requestScope.user.birthday.month}<br>
    ${requestScope.user.birthdayStr}<br>

    <h3>获取域中存储的List集合</h3>
    ${requestScope.list[0]}<br>
    ${list[1]}<br>
    ${list[2].name}<br>

    <h3>获取域中存储的Map集合</h3>
    ${requestScope.map.score}<br>
    ${requestScope.map["hobby"]}<br>
    ${requestScope.map["user"].name}<br>


    <%--获取对象：
    ${域名称.键名.属性名}       域名称可以省略
    属性名为意思：
        例如有一个getter方法public String getName()
        那么属性就是：getName 去掉get 再将首字母转换为小写 即name
                    getName     Name    name
    所以.属性名  实际上是去调用对应的getter方法--%>

    <%--获取List
    ${域名称.键名[索引]}
    --%>

    <%--获取Map
    ${域名称.键名.key名称}
    或
    ${域名称.键名["key名称"]}
    --%>
</body>
</html>
