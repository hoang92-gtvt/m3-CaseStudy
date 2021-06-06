<%--
  Created by IntelliJ IDEA.
  User: YEN
  Date: 6/2/2021
  Time: 9:57 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách Phiếu mượn</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<h1> Danh sách Phiếu mượn cho khách</h1>
<a href="/user?action=admin" class="btn btn-primary active" role="button" data-bs-toggle="button" aria-pressed="true">Quay về Home</a>
<table class="table table-striped table-hover">
    <tr >
        <td >Mã phiếu</td>
        <td >Ngày mượn</td>

        <td >Hạn trả sách</td>
        <td >Tên người mượn</td>
        <td >Tình trạng phiếu mượn</td>
        <td >Tên sách</td>


        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items="${pmList}" var="phieumuon">
        <tr class="table-primary">
            <td>${phieumuon.identity}</td>
            <td>${phieumuon.date}</td>
            <td>${phieumuon.duedate}</td>
            <td>${phieumuon.user.name}</td>
            <td>${phieumuon.statusPM.name}</td>
            <td><c:forEach items="${phieumuon.getBookList()}" var="book">
                <span>${book.name}</span>
            </c:forEach>

            </td>


            <td class="table-primary"><a href="/phieumuon?action=edit&id=${phieumuon.id}">edit</a></td>
            <td class="table-primary"><a href="/phieumuon?action=delete&id=${phieumuon.id}">delete</a></td>

        </tr>

    </c:forEach>

</table>

<a href="/phieumuon?action=create">Thêm một phiếu Mượn</a>



</body>
</html>