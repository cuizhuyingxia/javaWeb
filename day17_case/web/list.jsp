<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        /*为什么这个方法，不用放在window.onload里面呢？
        因为这个方法中没有document获取对象之类的操作，所以不用管页面是否加载完毕
        */
        function delUser(id) {
            if (confirm("您确定要删除吗？")) {
                // 执行删除
                location.href = "${pageContext.request.contextPath}/delUserServlet?id=" + id;
            }
        }

        /*
        而这个方法中，需要使用document获取对象，所以需要放在window.onload里面，
        因为只有页面加载完毕，才能获取到对象
         */
        window.onload = function () {
            // 给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function () {
                // 判断是否有选中条目
                var cbs = document.getElementsByName("uid");    // 获取所有checkbox，除了第一个用来做全选和全不选的
                var flag = false;
                for (var i = 0; i <= cbs.length; i ++) {
                    if (cbs[i].checked) {   // 如果有一个被选中
                        flag = true;
                        break;
                    }
                }
                // 执行删除
                if (flag) {
                    if (confirm("您确定要删除选中的条目吗")) {
                        // 提交表单
                        document.getElementById("form").submit();
                    }
                }
            };

            // 1. 获取用来做全选和全不选的checkbox
            document.getElementById("firstCb").onclick = function () {
                // 2. 获取其它所有的checkbox
                var cbs = document.getElementsByName("uid");
                // 3. 遍历
                for (var i = 0; i <= cbs.length; i ++) {
                    // 4. 设置这些cbs[i]的checked状态 = firstCb.checked
                    cbs[i].checked = this.checked;
                }
            }
        };


    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float:left;">
        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" class="form-control"  value="${condition.name[0]}" id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputName2">籍贯</label>
                <input type="text" name="address" class="form-control" value="${condition.address[0]}" id="exampleInputBirthplace2">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" name="email" class="form-control" value="${condition.email[0]}" id="exampleInputEmail2">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float:right; margin: 5px">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
    </div>
    <form id="form" action="${pageContext.request.contextPath}/delSelectServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pageBean.list}" var="user" varStatus="status">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.id}"></td>
                    <td>${status.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td>
                        <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" href="javascript:delUser(${user.id});">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">

                <%--上一页--%>
                <%--如果当前页等于1，则提示用户不要再点击上一页了--%>
                <c:if test="${pageBean.currentPage == 1}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pageBean.currentPage - 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${pageBean.currentPage != 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pageBean.currentPage - 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <%--当前页--%>
                <c:forEach begin="1" end="${pageBean.totalPage}" var="i" >
                    <%--设置当前页为选中状态--%>
                    <c:if test="${pageBean.currentPage == i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                    </c:if>

                    <c:if test="${pageBean.currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                    </c:if>
                </c:forEach>

                <%--下一页--%>
                <%--如果当前页等于总页码，则提示用户不要再点击下一页了--%>
                <c:if test="${pageBean.currentPage == pageBean.totalPage}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pageBean.currentPage + 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${pageBean.currentPage != pageBean.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pageBean.currentPage + 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>

                <span style="font-size: 25px; margin-left: 5px">
                    共${pageBean.totalCount}条记录，共${pageBean.totalPage}页
                </span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
