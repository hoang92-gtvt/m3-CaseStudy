<%--
  Created by IntelliJ IDEA.
  User: YEN
  Date: 6/4/2021
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>


<form action="/user" method="post">
    <div>
        <label>User</label><br>
        <input type="text" name= "userName" placeholder="Tên đăng nhập"><br>
        <label>PassWord</label><br>
        <input type="text" name= "pass" placeholder="Nhập mật khẩu"><br>

        <button id="id1">Login</button>
    </div>

</form>


</body>
</html>

<style>

    body{
        background-image: url("image/backgound.jpg");
    }
    form {
        width: 30%;
        height: 200px;
        background: violet;
        position: fixed;
        left:30%;
        top : 25%;
        border-style: solid;
        border-radius: 5px;
    }
    div {
        opacity: 0.5;
        filter: alpha(opacity=50);
        position: absolute;
        left:90px;
        top : 20px;
    }
    id1 {
        position: absolute;
        left:90px;
        top :200px;
    }

    input{
        width: 150%;
    }


</style>