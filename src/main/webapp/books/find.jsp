

<%--
  Created by IntelliJ IDEA.
  User: YEN
  Date: 6/6/2021
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>find book</title>
</head>
<body background="/image/backgound.jpg">


<form  method="post">
    <div>
        <label>Tên sách</label><br>
        <input type="text" name= "nameBook" ><br>
        <label>Thể Loại sách</label><br>
        <select name="category_id">
            <option value =""> No choose</option>
            <c:forEach items="${categories}" var="category">
              <option value =${category.id}> ${category.name} </option>
            </c:forEach>

        </select>
        <br>


        <button id="id1">Tìm kiếm</button>
    </div>

</form>


</body>
</html>

<style>


    form {
        width: 30%;
        height: 200px;
        background: #257EB7;
        position: fixed;
        left:30%;
        top : 25%;
        border-style: solid;
        border-radius: 5px;
    }
    div {
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