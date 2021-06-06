<%--
  Created by IntelliJ IDEA.
  User: YEN
  Date: 6/5/2021
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">--%>
</head>

<body id="myPage">

<!-- Sidebar on click -->
<nav class="w3-sidebar w3-bar-block w3-white w3-card w3-animate-left w3-xxlarge" style="display:none;z-index:2" id="mySidebar">
    <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-display-topright w3-text-teal">Close
        <i class="fa fa-remove"></i>
    </a>
    <a href="#" class="w3-bar-item w3-button">Link 1</a>
    <a href="#" class="w3-bar-item w3-button">Link 2</a>
    <a href="#" class="w3-bar-item w3-button">Link 3</a>
    <a href="#" class="w3-bar-item w3-button">Link 4</a>
    <a href="#" class="w3-bar-item w3-button">Link 5</a>
</nav>

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-theme-d2 w3-left-align">
        <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-hover-white w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fa fa-bars"></i></a>
        <a href="/book" class="w3-bar-item w3-button w3-teal"><i class="fa fa-home w3-margin-right"></i>BOOK </a>
        <a href="/phieumuon" class="w3-bar-item w3-button w3-hide-small w3-hover-white">PHIEUMUON</a>
        <a href="#" class="w3-bar-item w3-button w3-hide-small w3-hover-white" aria-disabled="true" >USER</a>
        <a href="#" class="w3-bar-item w3-button w3-hide-small w3-hover-white" aria-disabled="true" >NXB</a>
        <a href="#" class="w3-bar-item w3-button w3-hide-small w3-hover-white" aria-disabled="true" >CATEGORY</a>
        <div class="w3-dropdown-hover w3-hide-small">
            <%--            <button class="w3-button" title="Notifications">Dropdown <i class="fa fa-caret-down"></i></button>--%>
            <%--            <div class="w3-dropdown-content w3-card-4 w3-bar-block">--%>
            <%--                <a href="#" class="w3-bar-item w3-button">Link</a>--%>
            <%--                <a href="#" class="w3-bar-item w3-button">Link</a>--%>
            <%--                <a href="#" class="w3-bar-item w3-button">Link</a>--%>
            <%--            </div>--%>
        </div>
        <span class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-teal">${role}</span>
        <span id="user_id"  class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-teal">${name}</span>

        <a href="#" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-teal" title="Search"><i class="fa fa-search"></i></a>

    </div>

</div>



<!-- Team Container -->
<div class="w3-container w3-padding-64 w3-center" id="team">
    <h2>NHÀ XUẤT BẢN</h2>
    <p>Địa chỉ in ấn , phát hành sách</p>

    <div class="w3-row"><br>
        <c:forEach items="${nxbList}" var="nxb">

            <div class="w3-quarter">
                    <%--            <img src="/w3images/avatar.jpg" alt="Boss" style="width:45%" class="w3-circle w3-hover-opacity">--%>
                <h3>${nxb.name}</h3>
                    <%--            <p>Web Designer</p>--%>
            </div>
        </c:forEach>


    </div>
</div>

<!-- Work Row -->
<div class="w3-row-padding w3-padding-64 w3-theme-l1" id="work">

    <%--    <div class="w3-quarter">--%>
    <h2 style="text-align: center">Đầu Sách</h2>
    <p style="text-align: center">Minh họa đầu sách và thông tin sách</p>
    <%--    </div>--%>

    <c:forEach items="${bookList}" var="book" >
        <div class="w3-quarter">
            <div class="w3-card w3-white">
                <img src="${book.urlOfImage}" alt="Snow" style="width:100% ; height: 300px"/>
                <div class="w3-container">
                    <h3>${book.name}</h3>
                    <c:forEach items="${book.categories}" var="category">
                        <b>${category.name} </b>
                    </c:forEach>

                    <p>${book.description}</p>

                </div>
            </div>
        </div>
    </c:forEach>



</div>

<%--<!-- Container -->--%>
<%--<div class="w3-container" style="position:relative">--%>
<%--    <a onclick="w3_open()" class="w3-button w3-xlarge w3-circle w3-teal"--%>
<%--       style="position:absolute;top:-28px;right:24px">+</a>--%>
<%--</div>--%>




<%--<img src="/w3images/map.jpg" class="w3-image w3-greyscale-min" style="width:100%;">--%>

<!-- Footer -->
<footer class="w3-container w3-padding-32 w3-theme-d1 w3-center">
    <h4>Follow Us</h4>
    <a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Facebook"><i class="fa fa-facebook"></i></a>
    <a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Twitter"><i class="fa fa-twitter"></i></a>
    <a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Google +"><i class="fa fa-google-plus"></i></a>
    <a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Google +"><i class="fa fa-instagram"></i></a>
    <a class="w3-button w3-large w3-teal w3-hide-small" href="javascript:void(0)" title="Linkedin"><i class="fa fa-linkedin"></i></a>
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">hoang92codegym</a></p>

    <div style="position:relative;bottom:100px;z-index:1;" class="w3-tooltip w3-right">
        <span class="w3-text w3-padding w3-teal w3-hide-small">Go To Top</span>
        <a class="w3-button w3-theme" href="#myPage"><span class="w3-xlarge">
    <i class="fa fa-chevron-circle-up"></i></span></a>
    </div>
</footer>

<script>
    // Script for side navigation
    function w3_open() {
        var x = document.getElementById("mySidebar");
        x.style.width = "300px";
        x.style.paddingTop = "10%";
        x.style.display = "block";
    }

    // Close side navigation
    function w3_close() {
        document.getElementById("mySidebar").style.display = "none";
    }

    // Used to toggle the menu on smaller screens when clicking on the menu button
    function openNav() {
        var x = document.getElementById("navDemo");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }
</script>

</body>
</html>


