<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>

    <script>
        window.onload = function () {
            // 1. 获取验证码图片对象
            document.getElementById("changeImg").onclick = function () {
                // 2. 修改图片的src属性，以达到点击图片后切换图片的效果
                this.src = "/day16/checkCodeServlet?" + new Date().getTime();
            }
        }
    </script>

    <style>
        div {
            color: red;
        }
    </style>
</head>
<body>
    <form action="/day16/loginServlet" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>

            <tr>
                <td>密码</td>
                <td><input type="text" name="password"></td>
            </tr>

            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkCode"></td>
            </tr>

            <tr>
                <td colspan="2"><img id="changeImg" src="/day16/checkCodeServlet"></td>
            </tr>

            <tr>
                <td colspan="2"><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
    <div><%=request.getAttribute("checkCode_err") == null ? "" : request.getAttribute("checkCode_err")%></div>
    <div><%=request.getAttribute("login_err") == null ? "" : request.getAttribute("login_err")%></div>
</body>
</html>
