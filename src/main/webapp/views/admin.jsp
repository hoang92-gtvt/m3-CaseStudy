<%--
  Created by IntelliJ IDEA.
  User: YEN
  Date: 6/5/2021
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<h1> Giao diện admin </h1>

<%--<button ><a href="/book"> BOOK</a></button>--%>
<%--<button ><a href="/category"> CATEGORY</a></button>--%>
<%--<button ><a href="/nxb"> NHA SUAT BAN</a></button>--%>
<%--<button ><a href="/phieumuon"> PHIEU MUON</a></button>--%>

<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" aria-current="page" href="/book">BOOK</a>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="/phieumuon" role="button" aria-expanded="false">PHIEU MUON</a>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Show List</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Separated link</a></li>
        </ul>
    </li>
    <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">NXB</a>
    </li>
    <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">USERS</a>
    </li>
</ul>


</body>
</html>
