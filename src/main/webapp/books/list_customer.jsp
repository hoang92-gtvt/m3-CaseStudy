<%--
  Created by IntelliJ IDEA.
  User: YEN
  Date: 6/5/2021
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách Sách</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>
<h1> Danh sách Book có trong Thư viện </h1>
<a href="/book?action=find" class="btn btn-primary" role="button" data-bs-toggle="button">Tìm Kiếm </a>
<a href="/book?action=customer" class="btn btn-primary active" role="button" data-bs-toggle="button" aria-pressed="true">Quay trở về</a>
<a href="/user?action=customer" class="btn btn-primary active" role="button" data-bs-toggle="button" aria-pressed="true">Quay trở Home</a>
<a href="#" class="btn btn-primary disabled" tabindex="-1" aria-disabled="true" role="button" data-bs-toggle="button">None</a>
<table class="table table-striped table-hover">
    <tr >
        <td >Tên Sách</td>
        <td >Nội dung sách</td>

        <td >Tình trạng</td>
        <td >Nhà xuất bản</td>
        <td >Loại sách</td>
        <td>Hình ảnh sách </td>



    </tr>
    <c:forEach items="${bookList}" var="phieumuon">
        <tr class="table-primary">
            <td>${phieumuon.name}</td>
            <td>${phieumuon.description}</td>
            <td>${phieumuon.statusBook.name}</td>
            <td>${phieumuon.nxb.name}</td>
            <td><c:forEach items="${phieumuon.categories}" var="category">
                <span>${category.name}</span>
            </c:forEach>

            </td>
            <td>${phieumuon.urlOfImage}</td>



        </tr>

    </c:forEach>


</table>






</body>
</html>
