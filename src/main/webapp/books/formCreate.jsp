<%--
  Created by IntelliJ IDEA.
  User: YEN
  Date: 6/3/2021
  Time: 11:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Form Tao moi</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>
<h1>FORM CREAT NEW BOOK</h1>
<form class="mb-3" method="post">
    <div class="mb-3">
        <label  class="form-label">Tên sách</label>
        <input type="text" class="form-control" name="name" placeholder="Sách ABC">
    </div>


    <div class="mb-3">
        <label  class="form-label">Mổ tả</label>
        <input type="text" class="form-control" name="description" placeholder="Hướng dẫn ....">
    </div>

   <div class="mb-3">
    <label  class="form-label">Tình trạng sách</label>
       <br>

       <c:forEach items="${statusBooks}" var="status">
       <input  type="radio" name="status" value="${status.id}">
       <label>${status.name}</label>

        </c:forEach>


   </div>


    <div class="mb-3">
        <label  class="form-label">Nhà xuất bản sách </label>
        <br>
        <select>
        <c:forEach items="${nxbList}" var="nxb">

            <option name="nxb" value="${nxb.id}">${nxb.name}</option>

        </c:forEach>
        </select>


    </div>


    <div class="mb-3">
        <label  class="form-label">Đường dẫn link ảnh</label>
        <input type="text" class="form-control" name="urlOfImage" placeholder="....*png">
    </div>


    <div class="mb-3">
        <label  class="form-label">Loại sách </label>
        <br>

        <select multiple>
        <c:forEach items="${categories}" var="category">

            <option name="category" value="${category.id}"> ${category.name}</option>

        </c:forEach>
        </select>
    </div>

    <button type="submit">Create New Book</button>
</form>



</body>
</html>
<style>
    .form-label {
        font-weight: bolder;
        font-size: 110% ;
    }
</style>
